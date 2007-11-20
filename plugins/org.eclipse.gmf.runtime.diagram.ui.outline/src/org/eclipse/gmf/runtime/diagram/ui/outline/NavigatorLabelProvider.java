/***********************************************************************
 * Copyright (c) 2007 Anyware Technologies
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Anyware Technologies - initial API and implementation
 **********************************************************************/

package org.eclipse.gmf.runtime.diagram.ui.outline;

import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

/**
 * This Label Provider displays informations about model and diagrams. For the
 * model, this provider delegates informations computing to the model label
 * provider. <br>
 * creation : 7 dec. 2004
 * 
 * @author <a href="mailto:david.sciamma@anyware-tech.com">David Sciamma </a>
 */
public class NavigatorLabelProvider extends LabelProvider {

	/** The delegated model label provider */
	private ILabelProvider delegatedModelProvider;

	/**
	 * Constructor
	 * 
	 * @param delegatedProvider
	 *            the delegated label provider of the model
	 */
	public NavigatorLabelProvider(ILabelProvider delegatedProvider) {
		delegatedModelProvider = delegatedProvider;
	}

	/**
	 * @see org.eclipse.jface.viewers.ILabelProvider#getImage(java.lang.Object)
	 */
	public Image getImage(Object element) {
		if (element instanceof Diagram) {
			Diagram diag = (Diagram) element;

			return getDiagramIcon(diag);
		}

		return delegatedModelProvider.getImage(element);
	}

	/**
	 * @see org.eclipse.jface.viewers.ILabelProvider#getText(java.lang.Object)
	 */
	public String getText(Object element) {
		if (element instanceof AdditionalResources) {
			return "Additional Resources";
		}
		if (element instanceof Diagram) {
			Diagram diag = (Diagram) element;

			return getDiagramText(diag);
		}

		return delegatedModelProvider.getText(element);
	}

	/**
	 * Compose the diagram label
	 * 
	 * @param d
	 *            the diagram
	 * @return the diagram label
	 */
	private String getDiagramText(Diagram d) {
		String name = d.getName();
		String id = d.getType();
		String diagramTypeName = "Diagram";
		if (id != null && !"".equals(id)) {
			// TODO Change This !
			// DiagramDescriptor diagDesc =
			// DiagramsManager.getInstance().find(id);
			// if (diagDesc != null)
			// {
			// diagramTypeName = diagDesc.getName();
			// }
		}

		return name == null || name.length() == 0 ? diagramTypeName : diagramTypeName + " " + name;
	}

	/**
	 * Get the diagram icon
	 * 
	 * @param d
	 *            the diagram
	 * @return the diagram label
	 */
	private Image getDiagramIcon(Diagram d) {
		String id = d.getType();
		if (id != null && !"".equals(id)) {
			// TODO Change This !
			// DiagramDescriptor diagDesc =
			// DiagramsManager.getInstance().find(id);
			// if (diagDesc != null)
			// {
			// Image icon = diagDesc.getIcon();
			// if (icon != null)
			// {
			// return icon;
			// }
			// }
		}

		return delegatedModelProvider.getImage(d);
	}
}