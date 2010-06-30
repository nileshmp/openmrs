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
package org.openmrs.api;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.openmrs.Concept;
import org.openmrs.Location;
import org.openmrs.Patient;
import org.openmrs.Visit;
import org.openmrs.VisitType;


public interface VisitService extends OpenmrsService {
	
	/**
	 * Get all visits
	 * 
	 * @should get all visits
	 * @return List of all visits
	 */
	public List<Visit> getAllVisits();
	
	/**
	 * Saves the given visit
	 * 
	 * @should save visit
	 * @param visit
	 * @return saved visit
	 */
    public Visit saveVisit(Visit visit);
	
	/**
	 * Get visits based on the input parameters.
	 * 
	 * @should get visits by visit type
	 * @should get visits by patients
	 * @should get visits by locations
	 * @should get visits by start reasons
	 * @should get visits by end reasons
	 * @should get visits started between the given dates
	 * @should get visits ended between the given dates
	 * @param visitType
	 * @param patients
	 * @param locations
	 * @param minStartDatetime
	 * @param maxStartDatetime
	 * @param minEndDatetime
	 * @param maxEndDatetime
	 * @param startReasons
	 * @param endReasons
	 * @return
	 */
	public List<Visit> getVisits(VisitType visitType, Collection<Patient> patients, Collection<Location> locations,
	                             Date minStartDatetime, Date maxStartDatetime, Date minEndDatetime, Date maxEndDatetime,
	                             Collection<Concept> startReasons, Collection<Concept> endReasons);
	
	/**
	 * Gets list of visits by patient.
	 * 
	 * @should get visits by patient
	 * @param patient
	 * @return
	 */
	public List<Visit> getVisitByPatient(Patient patient);
}
