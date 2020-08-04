package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import user.Admin;

public class AdminController {

	/**
	 * a list of admin
	 */

	private ArrayList<Admin> adminList;

	public AdminController() {
		adminList = new ArrayList<>();
		loadData();
	}

	/**
	 * Add new admin and save !
	 */

	public void addAdmin(Admin admin) {
		adminList.add(admin);
		saveData();
	}

	/**
	 * Save all data
	 *
	 */

	public void saveData() {
		File adminData = new File("files//admin.bin");

		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(new FileOutputStream(adminData));
			oos.writeObject(adminList);
			oos.close();
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		}

	}

	/**
	 * Load all data
	 *
	 */

	@SuppressWarnings("unchecked")
	public void loadData() {
		File adminData = new File("files/admin.bin");
		try {

			if (!adminData.exists()) {
				adminList.add(new Admin("abdalla", "abdalla@gmail.com", "1"));
				adminData.createNewFile();
				saveData();
			}
			ObjectInputStream ois;

			ois = new ObjectInputStream(new FileInputStream(adminData));
			adminList = (ArrayList<Admin>) ois.readObject();
			ois.close();
		} catch (FileNotFoundException | ClassNotFoundException e) {
		} catch (IOException e) {
		}

	}

	/**
	 * Check if the given admin email is already registered in the system
	 * 
	 * @param email    The given email of admin
	 * @param password The given password of admin
	 * @return true if this admin is registered. otherwise,return false
	 */

	public boolean isAdmin(String email, String password) {
		for (Admin admin : adminList)
			if (admin.getEmail().equals(email) && admin.getPassword().equals(password))
				return true;

		return false;
	}

	/**
	 * Getting the admin list
	 * 
	 * @return The adminList
	 */

	public ArrayList<Admin> getAdminList() {
		return adminList;
	}

	/**
	 * change the admin list to a new one
	 * 
	 * @return The new adminList
	 */

	public void setAdminList(ArrayList<Admin> adminList) {
		this.adminList = adminList;
	}

}
