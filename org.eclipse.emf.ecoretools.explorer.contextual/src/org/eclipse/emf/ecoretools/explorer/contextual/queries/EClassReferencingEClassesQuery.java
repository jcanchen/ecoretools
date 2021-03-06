/*******************************************************************************
 * Copyright (c) 2016 Thales Global Services S.A.S.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *   Thales Global Services S.A.S - initial API and implementation
 ******************************************************************************/

package org.eclipse.emf.ecoretools.explorer.contextual.queries;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.amalgam.explorer.contextual.core.query.IQuery;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecoretools.explorer.contextual.helper.EClassReferencesHelper;

/**
 * @author Boubekeur Zendagui
 */
public class EClassReferencingEClassesQuery implements IQuery {

	@Override
	public List<Object> compute(Object object_p) {
		List<Object> result = new ArrayList<Object>();
		if (object_p instanceof EClass)
		{
			EClass eClass = (EClass) object_p;
			EPackage ePackage = (EPackage) EcoreUtil.getRootContainer(eClass);
			
			List<EClass> allEClasses = EClassReferencesHelper.getAllEClasses(ePackage);
			
			for (EClass eClass2 : allEClasses) 
			{
				EList<EReference> eAllReferences = eClass2.getEAllReferences();
				if (eAllReferences != null && eAllReferences.isEmpty() == false)
				{
					for (EReference eReference : eAllReferences) 
					{
						EClassifier eType = eReference.getEType();
						if (EcoreUtil.equals(eType, eClass))
						{
							result.add(eClass2);
							break;
						}
					}
				}
			}
		}
		return result;
	}
}
