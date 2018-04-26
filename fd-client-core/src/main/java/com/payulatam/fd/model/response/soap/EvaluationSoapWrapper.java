/**
 * PayU Latam - Copyright (c) 2013 - 2017
 * http://www.payu.com.co
 * Date: 19 de oct. de 2017
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

package com.payulatam.fd.model.response.soap;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import lombok.Data;

/**
 * Represents the evaluation result of the fraud detection service.
 * 
 * @author <a href="mailto:claudia.rodriguez@payulatam.com">Claudia Jimena Rodriguez</a>
 */
@Root
@Data
public class EvaluationSoapWrapper {

	/** The the evaluation result detail of the fraud detection service.*/
	@Element(name = "evaluacion", required = false)
	private EvaluationDetailSoapWrapper detail;

	/** The identifier of the decision made by the fraud detection service. */
	@Element(name = "decision", required = false)
	private Integer decision;

}
