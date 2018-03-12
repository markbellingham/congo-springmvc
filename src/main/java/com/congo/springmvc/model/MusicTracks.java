package com.congo.springmvc.model;

public class MusicTracks {
	
	private int id;
	private int recordingId;
	private String title;
	private int duration;
	private String strDuration;
	
	public MusicTracks(int id, int recordingId, String title, int duration) {
		super();
		this.id = id;
		this.recordingId = recordingId;
		this.title = title;
		this.duration = duration;
	}
	
	public MusicTracks(String title, String strDuration) {
		super();
		this.title = title;
		this.strDuration = strDuration;
	}

	public MusicTracks() {
		super();
	}

	@Override
	public String toString() {
		return "MusicTracks [id=" + id + ", recordingId=" + recordingId + ", title=" + title + ", duration=" + duration
				+ "]";
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public int getRecordingId() {
		return recordingId;
	}
	public void setRecordingId(int recordingId) {
		this.recordingId = recordingId;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	public String getStrDuration() {
		return strDuration;
	}

	public void setStrDuration(String strDuration) {
		this.strDuration = strDuration;
	}
	
	public static String formatDuration(int duration) {
		int minutes = duration / 60;
		int seconds = duration % 60;
		String formattedDuration = minutes + "m" + seconds + "s";
		return formattedDuration;
	}

}
