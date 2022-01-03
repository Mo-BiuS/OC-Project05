package com.safetyNet.Alerts.Model.Reply;

import java.util.ArrayList;
import java.util.List;

import com.safetyNet.Alerts.Model.Medicalrecord.Medicalrecords;
import com.safetyNet.Alerts.Model.Reply.Sub.ReqChildAlertAdult;
import com.safetyNet.Alerts.Model.Reply.Sub.ReqChildAlertChild;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/** 
 * Structured answer to the /childAlert uri
 * @author Mo-Bius
 */
@SuppressFBWarnings(value = "URF_UNREAD_PUBLIC_OR_PROTECTED_FIELD",
justification = "Ressources used as output in URI")
public class ReqChildAlert {
	public final List<ReqChildAlertChild> child;
	public final List<ReqChildAlertAdult> adult;
	
	/**
	 * The data send should be the concerned data, the sorting of said data is handled by the constructor
	 * @param childList
	 * @param adultList
	 */
	public ReqChildAlert(Medicalrecords childList, Medicalrecords adultList) {
		
		this.child = new ArrayList<ReqChildAlertChild>();
		childList.getMedicalrecords().forEach(item -> this.child.add(new ReqChildAlertChild(item)));
		
		this.adult = new ArrayList<ReqChildAlertAdult>();
		adultList.getMedicalrecords().forEach(item -> this.adult.add(new ReqChildAlertAdult(item)));
	}
	
	@Override
	public String toString() {
		String s = "\nChild :\n";
		for(ReqChildAlertChild c : child)s+=(c+"\n");
		s+="Adult :\n";
		for(ReqChildAlertAdult a : adult)s+=(a+"\n");
		
		return s;
	}
}
