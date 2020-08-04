package entity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import Movie.Session;
import user.Admin;

public class Cinema implements Serializable {
	private String cinemaCode;
	private ArrayList<Session> sessionList;
	private CinemaType cinemaType;
	private String fileName;

	public Cinema(String cinemaCode, CinemaType cinemaType, String fileName) {
		this.cinemaCode = cinemaCode;
		sessionList = new ArrayList<>();
		this.fileName = fileName;
		this.cinemaType = cinemaType;
	}

	public CinemaType getCinemaType() {
		return cinemaType;
	}

	public void setCinemaType(CinemaType cinemaType) {
		this.cinemaType = cinemaType;
	}

	public String getCinemaCode() {
		return cinemaCode;
	}

	public void setCinemaCode(String cinemaCode) {
		this.cinemaCode = cinemaCode;
	}

	public ArrayList<Session> getSessionList() {
		return sessionList;
	}

	public void setSessionList(ArrayList<Session> sessionList) {
		this.sessionList = sessionList;
	}

	public void addSession(Session session) {
		sessionList.add(session);
	}

	public void removeSession(Session session) {
		sessionList.remove(session);
	}

	@Override
	public String toString() {
		return "Cinema code:" + cinemaCode;
	}

	public void saveData() {
		File session = new File("files/" + fileName + ".bin");

		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(new FileOutputStream(session));
			oos.writeObject(sessionList);
			oos.close();
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		}

	}

	/**
	 * Load all data
	 *
	 */

	public void loadData() {
		File session = new File("files/" + fileName + ".bin");
		try {

			if (!session.exists())
				saveData();

			ObjectInputStream ois;

			ois = new ObjectInputStream(new FileInputStream(session));
			sessionList = (ArrayList<Session>) ois.readObject();
			ois.close();
		} catch (FileNotFoundException | ClassNotFoundException e) {
		} catch (IOException e) {
		}

	}
}
