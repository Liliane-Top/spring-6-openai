package nl.top.spring6aiintro.services;

import nl.top.spring6aiintro.model.*;

public interface OpenAIService {
    String getAnswer(String question);

    Answer getAnswer(Question question);

    CapitalResponse getCapital(CapitalRequest capitalRequest);

    CapitalWithInfoResponse getCapitalWithInfo(CapitalRequest capitalRequest);

}
