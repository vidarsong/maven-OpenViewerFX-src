/*
 * ===========================================
 * Java Pdf Extraction Decoding Access Library
 * ===========================================
 *
 * Project Info:  http://www.idrsolutions.com
 * Help section for developers at http://www.idrsolutions.com/support/
 *
 * (C) Copyright 1997-2015 IDRsolutions and Contributors.
 *
 * This file is part of JPedal/JPDF2HTML5
 *
     This library is free software; you can redistribute it and/or
    modify it under the terms of the GNU Lesser General Public
    License as published by the Free Software Foundation; either
    version 2.1 of the License, or (at your option) any later version.

    This library is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
    Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public
    License along with this library; if not, write to the Free Software
    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA


 *
 * ---------------
 * RecentDocumentsFactory.java
 * ---------------
 */

package org.jpedal.examples.viewer;

import org.jpedal.PdfDecoderInt;
import org.jpedal.examples.viewer.gui.generic.GUISearchWindow;
import org.jpedal.display.GUIThumbnailPanel;
import org.jpedal.examples.viewer.utils.PropertiesFile;
import org.jpedal.gui.GUIFactory;

public interface RecentDocumentsFactory {
    
    public String getPreviousDocument();

    public String getNextDocument();
    
    public void updateRecentDocuments(String[] recentDocs);
    
    public void enableRecentDocuments(boolean enable);
    
    public void clearRecentDocuments(PropertiesFile properties);
    
    public void addToFileList(String selectedFile);
    
    public void createMenuItems(String fileNameToAdd, int position, GUIFactory currentGUI,
                                Values commonValues, PdfDecoderInt decode_pdf, PropertiesFile properties,
                                GUIThumbnailPanel thumbnails, GUISearchWindow searchFrame);
    
}
