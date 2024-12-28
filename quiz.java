package com.example.quiz;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static class Question {
        private final int id;
        private final String question;
        private final String answer;
        private final List<String> options;

        public Question(int id, String question, String answer, List<String> options) {
            this.id = id;
            this.question = question;
            this.answer = answer;
            this.options = options;
        }

        public String getQuestion() {
            return question;
        }

        public String getAnswer() {
            return answer;
        }

        public List<String> getOptions() {
            return options;
        }
    }

    private final List<Question> questions = new ArrayList<>();
    private List<Question> selectedQuestions;
    private int currentQuestionIndex;
    private int score;
    private CountDownTimer timer;

    private TextView questionNumberTextView, questionTextView, timerTextView, scoreTextView;
    private LinearLayout optionsContainer;
    private Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        questionNumberTextView = findViewById(R.id.question_number);
        questionTextView = findViewById(R.id.quiz_question);
        timerTextView = findViewById(R.id.timer_text);
        optionsContainer = findViewById(R.id.options_container);
        startButton = findViewById(R.id.start_btn);
        scoreTextView = findViewById(R.id.score_display);

        createQuestions();

        startButton.setOnClickListener(v -> startQuiz());
    }

    private void createQuestions() {
        questions.add(new Question(1, "What is the purpose of anti-doping rules?", "To ensure fair play",
                List.of("To enhance performance", "To ensure fair play", "To avoid injuries", "To reduce costs")));

        questions.add(new Question(2, "What is doping in sports?", "The use of prohibited substances",
                List.of("The use of supplements", "The use of prohibited substances", "Regular training", "Legal medical treatments")));

        questions.add(new Question(3, "Which organization governs anti-doping globally?", "World Anti-Doping Agency (WADA)",
                List.of("World Health Organization (WHO)", "International Olympic Committee (IOC)", "World Anti-Doping Agency (WADA)", "United Nations (UN)")));

        questions.add(new Question(4, "What is one common type of doping substance?", "Anabolic steroids",
                List.of("Vitamins", "Anabolic steroids", "Caffeine", "Electrolytes")));

        questions.add(new Question(5, "What is blood doping?", "Increasing red blood cells to enhance performance",
                List.of("Reducing oxygen levels in blood", "Increasing red blood cells to enhance performance", "Taking painkillers", "Using anti-inflammatory drugs")));

        questions.add(new Question(6, "Which test is commonly used to detect doping?", "Urine test",
                List.of("MRI scan", "Urine test", "X-ray", "Blood pressure test")));

        questions.add(new Question(7, "What is the penalty for doping in sports?", "Suspension or ban from competition",
                List.of("Medal deduction", "Suspension or ban from competition", "Warning letter", "Reduction in rank")));

        questions.add(new Question(8, "Which of these is a prohibited substance in sports?", "Erythropoietin (EPO)",
                List.of("Vitamin C", "Erythropoietin (EPO)", "Creatine", "Protein powder")));

        questions.add(new Question(9, "What is the role of WADA?", "To oversee anti-doping efforts worldwide",
                List.of("To regulate athlete salaries", "To oversee anti-doping efforts worldwide", "To organize competitions", "To monitor team selections")));

        questions.add(new Question(10, "Why are diuretics banned in sports?", "They can mask other prohibited substances",
                List.of("They improve endurance", "They can mask other prohibited substances", "They reduce body weight", "They boost muscle growth")));

        questions.add(new Question(11, "What is the whereabouts rule in anti-doping?", "Athletes must provide their location for testing",
                List.of("Athletes must stay in training camps", "Athletes must provide their location for testing", "Athletes must avoid certain regions", "Athletes must disclose travel plans")));

        questions.add(new Question(12, "What is the purpose of TUE (Therapeutic Use Exemption)?", "To allow the use of necessary prohibited substances",
                List.of("To avoid testing", "To allow the use of necessary prohibited substances", "To increase performance", "To regulate substance prices")));

        questions.add(new Question(13, "What does anabolic refer to in anabolic steroids?", "Muscle building",
                List.of("Weight loss", "Endurance", "Muscle building", "Fat burning")));

        questions.add(new Question(14, "What is a side effect of anabolic steroid use?", "Liver damage",
                List.of("Improved vision", "Liver damage", "Increased flexibility", "Reduced heart rate")));

        questions.add(new Question(15, "What is the goal of doping control?", "To ensure fairness and athlete health",
                List.of("To increase competition", "To promote supplements", "To ensure fairness and athlete health", "To reduce expenses")));

        questions.add(new Question(16, "Why is gene doping banned?", "It provides an unfair genetic advantage",
                List.of("It reduces training time", "It provides an unfair genetic advantage", "It is costly", "It is hard to detect")));

        questions.add(new Question(17, "Which substance is banned due to its stimulant effects?", "Amphetamines",
                List.of("Glucose", "Amphetamines", "Iron supplements", "Calcium tablets")));

        questions.add(new Question(18, "What is one ethical issue with doping?", "It undermines the integrity of sports",
                List.of("It increases competition", "It undermines the integrity of sports", "It promotes team spirit", "It is expensive")));

        questions.add(new Question(19, "What is a biological passport in anti-doping?", "A record of an athlete's biological data",
                List.of("A travel document", "A record of an athlete's biological data", "A certification of eligibility", "A medical prescription")));

        questions.add(new Question(20, "Why is education important in anti-doping?", "To inform athletes about prohibited substances",
                List.of("To encourage supplement use", "To inform athletes about prohibited substances", "To increase participation in sports", "To reduce training costs")));
    }


    private void startQuiz() {
        currentQuestionIndex = 0;
        score = 0;
        startButton.setVisibility(View.GONE);
        scoreTextView.setText("Score: 0");
        Collections.shuffle(questions);
        selectedQuestions = questions.subList(0, 10);

        showQuestion(currentQuestionIndex);
    }

    private void showQuestion(int index) {
        if (timer != null) {
            timer.cancel();
        }

        Question currentQuestion = selectedQuestions.get(index);
        questionNumberTextView.setText("Question " + (index + 1) + "/10");
        questionTextView.setText(currentQuestion.getQuestion());
        optionsContainer.removeAllViews();

        for (String option : currentQuestion.getOptions()) {
            Button optionButton = new Button(this);
            optionButton.setText(option);
            optionButton.setOnClickListener(v -> checkAnswer(option, currentQuestion.getAnswer()));
            optionsContainer.addView(optionButton);
        }

        timerTextView.setText("Time Left: 30");
        timer = new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText("Time Left: " + millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
                Toast.makeText(MainActivity.this, "Time's up!", Toast.LENGTH_SHORT).show();
                moveToNextQuestion();
            }
        }.start();
    }

    private void checkAnswer(String selectedAnswer, String correctAnswer) {
        if (timer != null) {
            timer.cancel();
        }

        if (selectedAnswer.equals(correctAnswer)) {
            score++;
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Wrong answer.", Toast.LENGTH_SHORT).show();
        }

        scoreTextView.setText("Score: " + score);
        moveToNextQuestion();
    }

    private void moveToNextQuestion() {
        if (currentQuestionIndex < selectedQuestions.size() - 1) {
            currentQuestionIndex++;
            showQuestion(currentQuestionIndex);
        } else {
            finishQuiz();
        }
    }

    private void finishQuiz() {
        if (timer != null) {
            timer.cancel();
        }
        Toast.makeText(this, "Quiz Complete! Your score: " + score + "/10", Toast.LENGTH_LONG).show();
        startButton.setText("Restart Quiz");
        startButton.setVisibility(View.VISIBLE);
    }
}

