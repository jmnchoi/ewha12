package com.example.seethru;

public class HistoryData {
	private String date;
	private String imageBase64;
	private String result;

	public HistoryData(String date, String imageBase64, String result) {
		// TODO Auto-generated constructor stub
		this.date = date;
		this.imageBase64 = imageBase64;
		this.result = result;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getPhoto() {
		return imageBase64;
	}

	public void setPhoto(String imageBase64) {
		this.imageBase64 = imageBase64;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

}