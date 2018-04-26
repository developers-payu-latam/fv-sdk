/**
 * PayU Latam - Copyright (c) 2013 - 2017
 * http://www.payu.com.co
 * Date: 16 de nov. de 2017
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *    http://www.apache.org/licenses/LICENSE-2.0
 *    
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.payulatam.fd.model.response;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import lombok.Data;

/**
 * Encapsulates the scores assigned by the neuronal network.
 * 
 * @author <a href="mailto:claudia.rodriguez@payulatam.com">Claudia Jimena Rodriguez</a>
 */
@Root(name = "analisis-heuristico")
@Data
public class HeuristicAnalysis {

	/**
	 * The risk score assigned by the neuronal network. The score is in a range of 0.0 to 1.0, 0.0
	 * being the lowest risk and 1.0 the highest risk.
	 */
	@Element(name = "puntaje", required = false)
	private Double generalScore;

	/**
	 * The risk score assigned by the neural network to the email structure of the buyer user, the
	 * score is in a range of 0.0 to 1.0, 0.0 being the lowest risk and 1.0 the highest risk.
	 */
	@Element(name = "puntaje-email", required = false)
	private Double emailScore;

}
