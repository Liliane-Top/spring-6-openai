package nl.top.spring6aiintro.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.top.spring6aiintro.model.*;
import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.parser.BeanOutputParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class OpenAIServiceImpl implements OpenAIService {

    private final ChatClient chatClient;

    @Value("classpath:templates/get-capital-prompt.st")
    private Resource getCapitalPrompt;

    @Value("classpath:templates/get-capital-prompt-with-info.st")
    private Resource getGetCapitalPromptWithInfo;

    @Autowired
    ObjectMapper objectMapper;

    public OpenAIServiceImpl(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @Override
    public String getAnswer(String question) {
        PromptTemplate promptTemplate = new PromptTemplate(question);
        Prompt prompt = promptTemplate.create();
        ChatResponse response = chatClient.call(prompt);

        return response.getResult().getOutput().getContent();
    }

    @Override
    public Answer getAnswer(Question question) {
        PromptTemplate promptTemplate = new PromptTemplate(question.question());
        Prompt prompt = promptTemplate.create();
        ChatResponse response = chatClient.call(prompt);

        return new Answer(response.getResult().getOutput().getContent());
    }

    @Override
    public CapitalResponse getCapital(CapitalRequest capital) {
        var parser = new BeanOutputParser<>(CapitalResponse.class);
        String format = parser.getFormat();

        PromptTemplate promptTemplate = new PromptTemplate(getCapitalPrompt);
        Prompt prompt = promptTemplate.create(
                Map.of("stateOrCountry", capital.stateOrCountry(),
                        "format", format));
        ChatResponse response = chatClient.call(prompt);
        return parser.parse(response.getResult().getOutput().getContent());
    }

    @Override
    public CapitalWithInfoResponse getCapitalWithInfo(CapitalRequest capitalRequest) {
        var parser = new BeanOutputParser<>(CapitalWithInfoResponse.class);
        String format = parser.getFormat();

        PromptTemplate promptTemplate = new PromptTemplate(getCapitalPrompt);
        Prompt prompt = promptTemplate.create(
                Map.of("stateOrCountry", capitalRequest.stateOrCountry(),
                        "format", format));
        ChatResponse response = chatClient.call(prompt);
        System.out.println("response is: " + response.getResult().getOutput().getContent());

        return parser.parse(response.getResult().getOutput().getContent());
    }
}
