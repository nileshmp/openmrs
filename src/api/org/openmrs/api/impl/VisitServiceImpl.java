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
package org.openmrs.api.impl;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.openmrs.Concept;
import org.openmrs.Location;
import org.openmrs.Patient;
import org.openmrs.Visit;
import org.openmrs.VisitType;
import org.openmrs.api.VisitService;
import org.openmrs.api.db.hibernate.VisitDAO;

public class VisitServiceImpl extends BaseOpenmrsService implements VisitService {
	
	private VisitDAO visitDAO;
	
	public List<Visit> getAllVisits() {
		return getVisitDAO().getAllVisits();
	}
	
	public List<Visit> getVisits(VisitType visitType, Collection<Patient> patients, Collection<Location> locations,
	                             Date minStartDatetime, Date maxStartDatetime, Date minEndDatetime, Date maxEndDatetime,
	                             Collection<Concept> startReasons, Collection<Concept> endReasons) {
		return getVisitDAO().getVisits(visitType, patients, locations, minStartDatetime, maxStartDatetime, minEndDatetime,
		    maxEndDatetime, startReasons, endReasons);
	}

	public Visit saveVisit(Visit visit) {
		return getVisitDAO().saveVisit(visit);
	}
	
	public VisitDAO getVisitDAO() {
		return visitDAO;
	}
	
	public void setVisitDAO(VisitDAO visitDAO) {
		this.visitDAO = visitDAO;
	}
	
	/**
	 * @see org.openmrs.api.VisitService#getVisitByPatient(org.openmrs.Patient)
	 */
	@Override
	public List<Visit> getVisitByPatient(Patient patient) {
		return getVisitDAO().getVisits(null, Arrays.asList(patient), null, null, null, null, null, null, null);
	}
}
