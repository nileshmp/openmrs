/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs;

import java.util.Date;

public class Visit extends BaseOpenmrsData implements Auditable {
	
	private Location location;
	
	private String uuid;
	
	private Date dateStarted;
	
	private Date dateStopped;
	
	private Concept startReason;
	
	private Concept endReason;
	
	private VisitType visitType;
	
	private Integer visitId;
	
	private Patient patient;
	
	public Integer getId() {
		return getVisitId();
	}
	
	public void setId(Integer id) {
		setVisitId(id);
	}
	
	@Override
	public String getUuid() {
		return this.uuid;
	}
	
	@Override
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	public void setLocation(Location location) {
		this.location = location;
	}
	
	public void setDateStarted(Date dateStarted) {
		this.dateStarted = dateStarted;
	}
	
	public void setDateStopped(Date dateStopped) {
		this.dateStopped = dateStopped;
	}
	
	public void setStartReason(Concept startReason) {
		this.startReason = startReason;
	}
	
	public void setEndReason(Concept endReason) {
		this.endReason = endReason;
	}
	
	public void setVisitType(VisitType visitType) {
		this.visitType = visitType;
	}
	
	public Integer getVisitId() {
		return visitId;
	}
	
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	
	public void setVisitId(Integer visitId) {
		this.visitId = visitId;
	}
	
	public Date getDateStarted() {
		return dateStarted;
	}
	
	public Date getDateStopped() {
		return dateStopped;
	}
	
	public Concept getStartReason() {
		return startReason;
	}
	
	public Concept getEndReason() {
		return endReason;
	}
	
	public VisitType getVisitType() {
		return visitType;
	}
	
	public Patient getPatient() {
		return patient;
	}
	
	public Location getLocation() {
		return location;
	}
}
