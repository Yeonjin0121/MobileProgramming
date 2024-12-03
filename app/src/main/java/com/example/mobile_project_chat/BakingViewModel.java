package com.example.mobile_project_chat;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.ai.client.generativeai.GenerativeModel;
import com.google.ai.client.generativeai.java.GenerativeModelFutures;
import com.google.ai.client.generativeai.type.GenerateContentResponse;
import com.google.ai.client.generativeai.type.Content;
import com.google.ai.client.generativeai.type.GenerationConfig;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BakingViewModel extends ViewModel {
    private final MutableLiveData<UiState> uiState;
    private final GenerativeModelFutures generativeModel;
    private final ExecutorService executorService;

    private final String SYSTEM_PROMPT = "당신은 폐의약품 수거 앱 '리팜'의 고객 서비스 도우미입니다. " +
            "다음 질문-답변 쌍을 참고하여 사용자의 질문에 답변해주세요. 질문이 정확히 일치하지 않더라도 " +
            "가장 유사한 질문의 답변을 제공해주세요:\n\n" +
            "1. Q: 수거자와 의뢰자의 역할에 대해서 알고 싶어.\n" +
            "A: 수거자는 폐의약품을 의뢰자의 수거 신청 내역을 확인하고 폐의약품 수거 작업을 수행하는 역할을 수행합니다. 의뢰자는 폐의약품 수거를 의뢰합니다. 수거 신청 정보를 입력한 후 신청을 통해 편리하게 의약품을 처리합니다!\n\n" +
            "2. Q: 폐의약품 수거를 어떻게 신청하면 되나요?\n" +
            "A: 홈 탭에서 수거 신청 패널을 클릭하고 절차를 따라서 신청해주시면 됩니다!\n\n" +
            "3. Q: 의약품은 어떻게 배출을 처리하면 될까?\n" +
            "A: 홈 탭에서 이용 방법 패널을 클릭하면 됩니다!\n\n" +
            "4. Q: 수거 정보에서 사진은 어떤 사진을 업로드 하면 되나요?\n" +
            "A: 폐의약품 수거자가 수거 약품에 대한 정보를 얻고, 보다 수월하게 수거 작업을 진행할 수 있도록 약품의 사진, 전체 포장 및 배출된 사진을 업로드 해주시면 됩니다.\n\n" +
            "5. Q: 내가 이전에 수거 신청을 한 내역을 확인하고 싶어.\n" +
            "A: 수거 내역 탭에서 세부 정보와 함께 확인할 수 있답니다!\n\n" +
            "6. Q: 수거 상태는 어떻게 확인해?\n" +
            "A: 수거 내역 탭에서 개별 수거 내역의 상세 페이지를 통해 확인이 가능합니다! 접수중, 수거 대기, 수거 완료의 순서로 확인할 수 있답니다.\n\n" +
            "7. Q: 어플 이름 리팜의 뜻이 뭐야?\n" +
            "A: Recycle + Pharmaceutical의 합성어로, 폐의약품의 올바른 수거를 통한 환경 보호를 의미하고 있습니다!\n\n" +
            "8. Q: 내가 수거자와 의뢰자 중 어떤 회원 유형으로 입장했는지 알고 싶어.\n" +
            "A: 마이페이지에서 \"회원유형\"을 통해 확인이 가능합니다!\n\n" +
            "9. Q: 폐의약품 수거함의 위치를 알고 싶어요.\n" +
            "A: https://map.seoul.go.kr/smgis2/short/6P4cd 스마트 서울맵의 폐의약품 전용 수거함 위치입니다. 웹사이트를 접속해서 확인해보세요!\n\n" +
            "10. Q: 우체통에서도 폐의약품 수거가 가능하지 않나요?\n" +
            "A: 네 맞습니다. https://www.koreapost.go.kr/extra/user/kpost/gps/gpsUserView.do# 전국 우체국 위치찾기 지도입니다. 해당 웹사이트에서 우체통 필터를 적용해 조회가 가능합니다!\n\n" +
            "위 질문들 중에서 가장 유사한 질문을 찾아 해당 답변을 제공해주세요. 답변은 정확히 위의 답변 중 하나여야 합니다.\n" +
            "만약 위 질문들과 전혀 관련이 없는 질문이 들어온다면 \"죄송합니다. 해당 질문에 대한 답변을 제공할 수 없습니다. 폐의약품 수거와 관련된 질문을 해주세요.\"라고 답변해주세요.";

    public BakingViewModel() {
        uiState = new MutableLiveData<>(UiState.Initial.getInstance());

        // GenerativeModel 초기화
        GenerativeModel model = new GenerativeModel(
                "gemini-pro",
                "AIzaSyDfiZ76J6BnyLlw3nFtiMwAczyhof3tDEk"  // API 키 직접 사용
        );
        generativeModel = GenerativeModelFutures.from(model);

        executorService = Executors.newSingleThreadExecutor();
    }

    public LiveData<UiState> getUiState() {
        return uiState;
    }

    public void sendPrompt(String userPrompt) {
        uiState.setValue(UiState.Loading.getInstance());

        try {
            // Content 생성 - 시스템 프롬프트와 사용자 입력 결합
            Content content = new Content.Builder()
                    .addText(SYSTEM_PROMPT + "\n\n사용자: " + userPrompt)
                    .build();

            // generateContent 메서드 호출
            ListenableFuture<GenerateContentResponse> future = generativeModel.generateContent(content);

            Futures.addCallback(
                    future,
                    new FutureCallback<GenerateContentResponse>() {
                        @Override
                        public void onSuccess(GenerateContentResponse result) {
                            try {
                                String outputContent = result.getText();
                                if (outputContent != null) {
                                    uiState.postValue(new UiState.Success(outputContent));
                                }
                            } catch (Exception e) {
                                onFailure(e);
                            }
                        }

                        @Override
                        public void onFailure(Throwable t) {
                            String errorMessage = t.getLocalizedMessage();
                            if (errorMessage == null) {
                                errorMessage = "Error occurred";
                            }
                            uiState.postValue(new UiState.Error(errorMessage));
                        }
                    },
                    MoreExecutors.directExecutor()
            );
        } catch (Exception e) {
            uiState.postValue(new UiState.Error(e.getLocalizedMessage()));
        }
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        executorService.shutdownNow();
    }
}