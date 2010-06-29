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
package org.openmrs.api.db.hibernate;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;
import org.openmrs.Concept;
import org.openmrs.Location;
import org.openmrs.Patient;
import org.openmrs.Visit;
import org.openmrs.VisitType;
import org.openmrs.api.db.DAOException;

public class HibernateVisitDAO implements VisitDAO {
	
	private SessionFactory sessionFactory;
	
	/**
	 * Sets the session factory
	 * 
	 * @param sessionFactory
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public Visit saveVisit(Visit visit) throws DAOException {
		getCurrentSession().saveOrUpdate(visit);
		return visit;
	}

	/**
	 * @see org.openmrs.api.db.hibernate.VisitDAO#getAllVisits()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Visit> getAllVisits() {
		return getCurrentSession().createQuery("from Visit visit").list();
	}
	
	/**
	 * Auto generated method comment
	 * 
	 * @return
	 */
	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
	
	/**
	 * @see org.openmrs.api.db.hibernate.VisitDAO#getVisits(org.openmrs.VisitType,
	 *      java.util.Collection, java.util.Collection, java.util.Date, java.util.Date,
	 *      java.util.Date, java.util.Date, java.util.Collection, java.util.Collection)
	 */
	@Override
	public List<Visit> getVisits(VisitType visitType, Collection<Patient> patients, Collection<Location> locations,
	                             Date minStartDatetime, Date maxStartDatetime, Date minEndDatetime, Date maxEndDatetime,
	                             Collection<Concept> startReasons, Collection<Concept> endReasons) {
		Criteria criteria = getCurrentSession().createCriteria(Visit.class);
		safeCriteria(visitType, criteria);
		if (patients != null)
			criteria.add(Restrictions.in("patient", patients));
		if (locations != null)
			criteria.add(Restrictions.in("location", locations));
		if (startReasons != null)
			criteria.add(Restrictions.in("startReason", startReasons));
		if (endReasons != null)
			criteria.add(Restrictions.in("endReason", endReasons));
		if (minStartDatetime != null)
			criteria.add(Restrictions.ge("dateStarted", minStartDatetime));
		if (maxStartDatetime != null)
			criteria.add(Restrictions.le("dateStarted", maxStartDatetime));
		if (minEndDatetime != null)
			criteria.add(Restrictions.ge("dateStopped", minEndDatetime));
		if (maxEndDatetime != null)
			criteria.add(Restrictions.le("dateStopped", maxEndDatetime));
		return criteria.list();
	}

	/**
     * Auto generated method comment
     * 
     * @param visitType
     * @param criteria
     */
    private void safeCriteria(VisitType visitType, Criteria criteria) {
	    if (visitType != null)
			criteria.add(Restrictions.eq("visitType", visitType));
    }
}
