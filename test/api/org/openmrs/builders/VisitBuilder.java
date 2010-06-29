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
package org.openmrs.builders;

import java.util.Date;

import org.openmrs.Concept;
import org.openmrs.Location;
import org.openmrs.Patient;
import org.openmrs.Visit;
import org.openmrs.VisitType;
import org.openmrs.api.context.Context;


public class VisitBuilder {
	
	private String uuid;
	
	private Integer visitId;

	private Location location = Context.getLocationService().getLocation(1);
	
	private Date dateStarted = new Date();
	
	private Date dateStopped = new Date();
	
	private Concept startReason = Context.getConceptService().getConcept(1);
	
	private Concept endReason = Context.getConceptService().getConcept(2);
	
	private VisitType visitType = VisitType.IN_PATIENT;
	
	private Patient patient = Context.getPatientService().getPatient(2);
	
	public VisitBuilder withLocation(Location location) {
		this.location = location;
		return this;
	}
	
	public VisitBuilder withUuid(String uuid) {
		this.uuid = uuid;
		return this;
	}
	
	public VisitBuilder withDateStarted(Date dateStarted) {
		this.dateStarted = dateStarted;
		return this;
	}
	
	public VisitBuilder withDateStopped(Date dateStopped) {
		this.dateStopped = dateStopped;
		return this;
	}
	
	public VisitBuilder withStartReason(Concept startReason) {
		this.startReason = startReason;
		return this;
	}
	
	public VisitBuilder withEndReason(Concept endReason) {
		this.endReason = endReason;
		return this;
	}
	
	public VisitBuilder withVisitType(VisitType visitType) {
		this.visitType = visitType;
		return this;
	}
	
	public VisitBuilder withVisitId(Integer visitId) {
		this.visitId = visitId;
		return this;
	}
	
	public VisitBuilder withPatient(Patient patient) {
		this.patient = patient;
		return this;
	}
	
	public Visit build() {
		Visit visit = new Visit();
		visit.setPatient(patient);
		visit.setLocation(location);
		visit.setDateStarted(dateStarted);
		visit.setDateStopped(dateStopped);
		visit.setStartReason(startReason);
		visit.setEndReason(endReason);
		visit.setVisitType(visitType);
		return visit;
	}

}
