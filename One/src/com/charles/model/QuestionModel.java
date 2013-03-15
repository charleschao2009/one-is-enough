package com.charles.model;

import java.util.List;

public class QuestionModel {

	private String quesiton_title;
	private List<String> question_content;
	private String answer_title;
	private List<String> answer_content;

	public QuestionModel(String quesiton_title, List<String> question_content,
			String answer_title, List<String> answer_content) {
		super();
		this.quesiton_title = quesiton_title;
		this.question_content = question_content;
		this.answer_title = answer_title;
		this.answer_content = answer_content;
	}

	public QuestionModel() {
	}

	public String getQuesiton_title() {
		return quesiton_title;
	}

	public void setQuesiton_title(String quesiton_title) {
		this.quesiton_title = quesiton_title;
	}

	public List<String> getQuestion_content() {
		return question_content;
	}

	public void setQuestion_content(List<String> question_content) {
		this.question_content = question_content;
	}

	public String getAnswer_title() {
		return answer_title;
	}

	public void setAnswer_title(String answer_title) {
		this.answer_title = answer_title;
	}

	public List<String> getAnswer_content() {
		return answer_content;
	}

	public void setAnswer_content(List<String> answer_content) {
		this.answer_content = answer_content;
	}

}
