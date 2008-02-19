/***********************************************************************
 * Copyright (c) 2008 Anyware Technologies
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    Anyware Technologies - initial API and implementation
 **********************************************************************/

package org.eclipse.gmf.tests.runtime.diagram.ui.core.bypassedclasses;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.ui.actions.DeleteFromModelAction;
import org.eclipse.ui.IWorkbenchPart;

/**
 * A DeleteFromModelAction action bypassed for create the right command
 * 
 * @author Simon Bernard
 */
public class DeleteFromModelBypassedAction extends DeleteFromModelAction {

	public List partsToDelete;

	public DeleteFromModelBypassedAction(IWorkbenchPart part, EditPart objectToDelete) {
		super(part);
		partsToDelete = new ArrayList();
		partsToDelete.add(objectToDelete);
		refresh();
	}

	public DeleteFromModelBypassedAction(IWorkbenchPart part, List objectsToDelete) {
		super(part);
		this.partsToDelete = objectsToDelete;
		refresh();
	}

	@Override
	protected List createOperationSet() {
		return partsToDelete;
	}

	@Override
	public Command getCommand() {
		// make it public
		return super.getCommand();
	}
}