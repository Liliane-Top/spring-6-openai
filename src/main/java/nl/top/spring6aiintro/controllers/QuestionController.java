package nl.top.spring6aiintro.controllers;

import nl.top.spring6aiintro.model.*;
import nl.top.spring6aiintro.services.OpenAIService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuestionController {

    private final OpenAIService openAIService;

    public QuestionController(OpenAIService openAIService) {
        this.openAIService = openAIService;
    }

    @PostMapping("/ask")
    public Answer askQuestion(@RequestBody Question question) {
        return openAIService.getAnswer(question);
    }

    @PostMapping("/capital")
    public CapitalResponse getCapital(@RequestBody CapitalRequest capitalRequest) {
        return openAIService.getCapital(capitalRequest);
    }

    @PostMapping("/capitalWithInfo")
    public CapitalWithInfoResponse getCapitalWithInfo(@RequestBody CapitalRequest capitalRequest) {
        return this.openAIService.getCapitalWithInfo(capitalRequest);
    }

}
