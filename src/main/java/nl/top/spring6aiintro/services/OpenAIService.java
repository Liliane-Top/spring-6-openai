package nl.top.spring6aiintro.services;

import nl.top.spring6aiintro.model.Answer;
import nl.top.spring6aiintro.model.Question;

public interface OpenAIService {
    String getAnswer(String question);

    Answer getAnswer(Question question);
}
