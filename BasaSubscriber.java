import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
public class BasaSubscriber {
	private String PhoneNumber;
	private String SubscriberData;
	private String Address;
	private double Debt;

	BasaSubscriber(String PhoneNumber, String SubscriberData, String Address, double Debt) {
		this.PhoneNumber = PhoneNumber;
		this.SubscriberData = SubscriberData;
		this.Address = Address;
		this.Debt = Debt;
	}
	BasaSubscriber(String PhoneNumber, double Debt) {
		this.PhoneNumber = PhoneNumber;
		this.Debt = Debt;
	}

	public String getPhoneNumber() {
		return PhoneNumber;
	}

	public String getSubscriberData() {
		return SubscriberData;
	}

	public String getAddress() {
		return Address;
	}

	public double getDebt() {
		return Debt;
	}

	public void setPhoneNumber(String PhoneNumber) {
		this.PhoneNumber = PhoneNumber;
	}

	public void setSubscriberData(String SubscriberData) {
		this.SubscriberData = SubscriberData;
	}

	public void setAddress(String Address) {
		this.Address = Address;
	}

	public void setDebt(double Debt) {
		this.Debt = Debt;
	}

	public void show() {
		System.out.println("Phone Number: " + getPhoneNumber());
		System.out.println("Subscriber Data: " + getSubscriberData());
		System.out.println("Address: " + getAddress());
		System.out.println("Debt: " + getDebt());
	}

	}

