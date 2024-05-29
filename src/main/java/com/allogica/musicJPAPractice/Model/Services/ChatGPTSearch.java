package com.allogica.musicJPAPractice.Model.Services;

import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;

public class ChatGPTSearch {
    public static String searchArtistOnline(String text) {
        OpenAiService service = new OpenAiService(System.getenv("GPTKEY"));


        CompletionRequest request = CompletionRequest.builder()
                .model("gpt-3.5-turbo-instruct")
                .prompt("Summarize the info of the following artist (be concise, max of 3 lines): " + text)
                .maxTokens(1000)
                .temperature(0.7)
                .build();


        var answer = service.createCompletion(request);
        return answer.getChoices().get(0).getText();
    }
}
