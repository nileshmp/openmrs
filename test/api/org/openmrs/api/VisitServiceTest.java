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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openmrs.Concept;
import org.openmrs.Location;
import org.openmrs.Patient;
import org.openmrs.Visit;
import org.openmrs.VisitType;
import org.openmrs.api.context.Context;
import org.openmrs.builders.VisitBuilder;
import org.openmrs.test.BaseContextSensitiveTest;
import org.openmrs.test.Verifies;

public class VisitServiceTest extends BaseContextSensitiveTest {
	
	protected static final String INITIAL_CONCEPTS_XML = "org/openmrs/api/include/ConceptServiceTest-initialConcepts.xml";
	
	@Before
	public void populateData() throws Exception {
		executeDataSet(INITIAL_CONCEPTS_XML);
	}

	@Test
	public void saveVisit_shouldSaveVisit() throws Exception {
		Visit visit = new VisitBuilder().build();
		Visit savedVisit = Context.getVisitService().saveVisit(visit);
		Assert.assertNotNull(savedVisit.getVisitId());
		Assert.assertNotNull(savedVisit.getId());
	}
	
	/**
	 * @see {@link VisitService#getAllVisits()}
	 */
	@Test
	@Verifies(value = "should get all visits", method = "getAllVisits()")
	public void getAllVisits_shouldGetAllVisits() throws Exception {
		Context.getVisitService().saveVisit(new VisitBuilder().build());
		Context.getVisitService().saveVisit(new VisitBuilder().build());
		List<Visit> visits = Context.getVisitService().getAllVisits();
		Assert.assertEquals(2, visits.size());
	}
	
	@Test
	public void getAllVisits_shouldGetVisitsByVisitType() throws Exception {
		Visit inVisit = new VisitBuilder().withVisitType(VisitType.IN_PATIENT).build();
		Visit outVisit = new VisitBuilder().withVisitType(VisitType.OUT_PATIENT).build();

		Context.getVisitService().saveVisit(inVisit);
		Context.getVisitService().saveVisit(outVisit);

		List<Visit> visits = Context.getVisitService().getVisits(VisitType.IN_PATIENT, null, null, null, null, null, null,
		    null, null);
		Assert.assertEquals(1, visits.size());
		Assert.assertEquals(inVisit.getId(), visits.get(0).getId());
	}
	
	@Test
	public void getAllVisits_shouldGetVisitsByPatients() throws Exception {
		Patient patient2 = Context.getPatientService().getPatient(2);
		Visit patient2Visit = new VisitBuilder().withPatient(patient2).build();
		
		Patient patient6 = Context.getPatientService().getPatient(6);
		Visit patient6Visit = new VisitBuilder().withPatient(patient6).build();

		Patient patient7 = Context.getPatientService().getPatient(7);
		Visit patient7Visit = new VisitBuilder().withPatient(patient7).build();
		
		Context.getVisitService().saveVisit(patient2Visit);
		Context.getVisitService().saveVisit(patient6Visit);
		Context.getVisitService().saveVisit(patient7Visit);

		Collection<Patient> patients = new ArrayList<Patient>();
		patients.add(patient6);
		patients.add(patient2);
		List<Visit> visits = Context.getVisitService().getVisits(null, patients, null, null, null, null, null, null, null);
		
		Assert.assertEquals(2, visits.size());
		Assert.assertTrue(visits.contains(patient6Visit));
		Assert.assertTrue(visits.contains(patient2Visit));
	}
	
	@Test
	public void getAllVisits_shouldGetVisitsByLocations() throws Exception {
		Location location1 = Context.getLocationService().getLocation(1);
		Location location2 = Context.getLocationService().getLocation(2);
		Location location3 = Context.getLocationService().getLocation(3);
		
		Visit location1Visit = new VisitBuilder().withLocation(location1).build();
		Visit location2Visit = new VisitBuilder().withLocation(location2).build();
		Visit location3Visit = new VisitBuilder().withLocation(location3).build();

		Context.getVisitService().saveVisit(location1Visit);
		Context.getVisitService().saveVisit(location2Visit);
		Context.getVisitService().saveVisit(location3Visit);
		
		Collection<Location> locations = new ArrayList<Location>();
		locations.add(location1);
		locations.add(location3);
		List<Visit> visits = Context.getVisitService().getVisits(null, null, locations, null, null, null, null, null, null);
		
		Assert.assertEquals(2, visits.size());
		Assert.assertTrue(visits.contains(location1Visit));
		Assert.assertTrue(visits.contains(location3Visit));
	}
	
	@Test
	public void getAllVisits_shouldGetVisitsByStartReasons() throws Exception {
		Concept startReason1 = Context.getConceptService().getConcept(1);
		Concept startReason2 = Context.getConceptService().getConcept(2);
		Concept startReason3 = Context.getConceptService().getConcept(3);
		
		Visit startReason1Visit = new VisitBuilder().withStartReason(startReason1).build();
		Visit startReason2Visit = new VisitBuilder().withStartReason(startReason2).build();
		Visit startReason3Visit = new VisitBuilder().withStartReason(startReason3).build();
		
		Context.getVisitService().saveVisit(startReason1Visit);
		Context.getVisitService().saveVisit(startReason2Visit);
		Context.getVisitService().saveVisit(startReason3Visit);
		
		Collection<Concept> startReasons = new ArrayList<Concept>();
		startReasons.add(startReason1);
		startReasons.add(startReason2);
		List<Visit> visits = Context.getVisitService().getVisits(null, null, null, null, null, null, null, startReasons,
		    null);
		
		Assert.assertEquals(2, visits.size());
		Assert.assertTrue(visits.contains(startReason1Visit));
		Assert.assertTrue(visits.contains(startReason2Visit));
	}
	
	@Test
	public void getAllVisits_shouldGetVisitsByEndReasons() throws Exception {
		Concept endReason1 = Context.getConceptService().getConcept(1);
		Concept endReason2 = Context.getConceptService().getConcept(2);
		Concept endReason3 = Context.getConceptService().getConcept(3);

		Visit endReason1Visit = new VisitBuilder().withEndReason(endReason1).build();
		Visit endReason2Visit = new VisitBuilder().withEndReason(endReason2).build();
		Visit endReason3Visit = new VisitBuilder().withEndReason(endReason3).build();
		
		Context.getVisitService().saveVisit(endReason1Visit);
		Context.getVisitService().saveVisit(endReason2Visit);
		Context.getVisitService().saveVisit(endReason3Visit);

		Collection<Concept> endReasons = new ArrayList<Concept>();
		endReasons.add(endReason1);
		endReasons.add(endReason2);
		List<Visit> visits = Context.getVisitService().getVisits(null, null, null, null, null, null, null, null, endReasons);
		
		Assert.assertEquals(2, visits.size());
		Assert.assertTrue(visits.contains(endReason1Visit));
		Assert.assertTrue(visits.contains(endReason2Visit));
	}

	@Test
	public void getAllVisits_shouldGetVisitsStartedBetweenTheGivenDates() throws Exception {
		Visit janVisit = new VisitBuilder().withDateStarted(date(1, 1, 2010)).build();
		Visit febVisit = new VisitBuilder().withDateStarted(date(1, 2, 2010)).build();
		Visit marchVisit = new VisitBuilder().withDateStarted(date(1, 3, 2010)).build();
		
		Context.getVisitService().saveVisit(janVisit);
		Context.getVisitService().saveVisit(febVisit);
		Context.getVisitService().saveVisit(marchVisit);
		
		List<Visit> visits = Context.getVisitService().getVisits(null, null, null, date(15, 1, 2010), date(15, 2, 2010),
		    null, null, null, null);
		
		Assert.assertEquals(1, visits.size());
		Assert.assertTrue(visits.contains(febVisit));
	}
	
	@Test
	public void getAllVisits_shouldGetVisitsEndedBetweenTheGivenDates() throws Exception {
		Visit janVisit = new VisitBuilder().withDateStopped(date(1, 1, 2010)).build();
		Visit febVisit = new VisitBuilder().withDateStopped(date(1, 2, 2010)).build();
		Visit marchVisit = new VisitBuilder().withDateStopped(date(1, 3, 2010)).build();
		
		Context.getVisitService().saveVisit(janVisit);
		Context.getVisitService().saveVisit(febVisit);
		Context.getVisitService().saveVisit(marchVisit);
		
		List<Visit> visits = Context.getVisitService().getVisits(null, null, null, null, null, date(15, 1, 2010),
		    date(15, 2, 2010), null, null);
		
		Assert.assertEquals(1, visits.size());
		Assert.assertTrue(visits.contains(febVisit));
	}
	
	@Test
	public void shouldGetVisitsByPatient() throws Exception {
		Patient patient2 = Context.getPatientService().getPatient(2);
		Visit patient2Visit = new VisitBuilder().withPatient(patient2).build();
		
		Patient patient6 = Context.getPatientService().getPatient(6);
		Visit patient6Visit = new VisitBuilder().withPatient(patient6).build();
		
		Patient patient7 = Context.getPatientService().getPatient(7);
		Visit patient7Visit = new VisitBuilder().withPatient(patient7).build();
		
		Context.getVisitService().saveVisit(patient2Visit);
		Context.getVisitService().saveVisit(patient6Visit);
		Context.getVisitService().saveVisit(patient7Visit);
		
		List<Visit> visits = Context.getVisitService().getVisitByPatient(patient2);
		
		Assert.assertEquals(1, visits.size());
		Assert.assertTrue(visits.contains(patient2Visit));
	}

	/**
	 * Auto generated method comment
	 * 
	 * @param day TODO
	 * @param month TODO
	 * @param year TODO
	 * @return
	 */
	private Date date(int day, int month, int year) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month, day);
		return calendar.getTime();
	}

}
