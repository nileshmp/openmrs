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
    public List<Visit> getAllVisits();

    public Visit saveVisit(Visit visit);
	
	public List<Visit> getVisits(VisitType visitType, Collection<Patient> patients, Collection<Location> locations,
	                             Date minStartDatetime, Date maxStartDatetime, Date minEndDatetime, Date maxEndDatetime,
	                             Collection<Concept> startReasons, Collection<Concept> endReasons);
	
	public List<Visit> getVisitByPatient(Patient patient);
}
