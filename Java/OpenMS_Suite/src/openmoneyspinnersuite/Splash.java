
// Open MoneySpinner Suite v1
// An open source business management software system written in Java and MySQL
// Recommended IDE is NetBeans IDE 7.0.1
// Support Web Site: http://www.milliscript.com
//
// Copyright (C) 2014, Abiodun Aremu, Ibadan/NIGERIA.
// Open MoneySpinner Suite is distributed under the terms of the Apache License version 2.0
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package openmoneyspinnersuite;

/**
 *
 * @author Abiodun Aremu
 */
import javax.swing.*;
import java.awt.*;
import javax.swing.border.LineBorder;

final class Splash extends JWindow
{
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(openmoneyspinnersuite.OpenMSApp.class).getContext().getResourceMap(Login.class);

        JLabel SplashLabel = new JLabel(resourceMap.getIcon("picLabel.icon"));
        Dimension screen = 	Toolkit.getDefaultToolkit().getScreenSize();
        Color cl = new Color (0, 0, 0);

        public Splash()
        {
	}
        public void display()
        {
		SplashLabel.setBorder (new LineBorder (cl, 1));

		getContentPane().add(SplashLabel,BorderLayout.CENTER);

		setSize(490,300);
		setLocation((screen.width - 490)/2,((screen.height-300)/2));
		this.setVisible(true);

        }
}
