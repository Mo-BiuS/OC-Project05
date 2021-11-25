package com.safetyNet.Alerts.Model.Reply;

import java.util.ArrayList;
import java.util.List;

import com.safetyNet.Alerts.Model.Medicalrecord.Medicalrecords;
import com.safetyNet.Alerts.Model.Reply.Sub.ChildAlertAdult;
import com.safetyNet.Alerts.Model.Reply.Sub.ChildAlertChild;

public class ChildAlert {
	public final List<ChildAlertChild> child;
	public final List<ChildAlertAdult> adult;
	public ChildAlert(Medicalrecords childList, Medicalrecords adultList) {
		
		this.child = new ArrayList<ChildAlertChild>();
		childList.getMedicalrecords().forEach(item -> this.child.add(new ChildAlertChild(item)));
		
		this.adult = new ArrayList<ChildAlertAdult>();
		adultList.getMedicalrecords().forEach(item -> this.adult.add(new ChildAlertAdult(item)));
	}
}
