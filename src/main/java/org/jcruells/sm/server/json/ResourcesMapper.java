package org.jcruells.sm.server.json;

import java.io.IOException;

import javax.persistence.Column;
import javax.persistence.Transient;

import org.jcruells.sm.server.repository.CheckIn;
import org.jcruells.sm.server.repository.Patient;
import org.jcruells.sm.server.repository.PatientMedication;
import org.springframework.hateoas.Resources;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.node.IntNode;

/**
 * Begin long explanation of why this class was created...
 * 
 * 
 * By default, Spring Data Rest uses a format called HATEOAS (http://en.wikipedia.org/wiki/HATEOAS)
 * to output the data returned from a Repository. The results from findAll(), findByName(), etc. are
 * wrapped in an Object called Resources. When this Resources object is converted to JSON, it adds
 * additional fields to the JSON so that we don't just get back a list of Video objects.
 * 
 * For our VideoRepository, the default output would like something like this for the /video :
 * 
 * {
    "_links": {
        "search": {
            "href": "http://localhost:8080/video/search"
        }
    },
    "_embedded": {
        "videos": [
            {
                "name": "Foo",
                "url": null,
                "duration": 100,
                "_links": {
                    "self": {
                        "href": "http://localhost:8080/video/1"
                    }
                }
            }
        ]
    }
}
 * You can comment out the Application.halObjectMapper() and rerun the application if you would
 * like to see what the default format looks like with full HATEOAS.
 * 
 * For this simple example, the extra HATEOAS "_embedded" and "_links" formatting for the top-level
 * JSON adds extra complexity. Because of the format, we can't just directly unmarshall this response
 * into a list of Video objects.
 * 
 * To simplify this example and make it possible to directly unmarshall the responses as a list of 
 * Video objects, this ObjectMapper overrides the default JSON marshalling of Spring Data Rest so
 * that it outputs this instead:
 * 
 * [
    {
        "name": "Foo",
        "url": null,
        "duration": 100,
        "_links": {
            "self": {
                "href": "http://localhost:8080/video/1"
            }
        }
    }
   ]
 * 
 * This alternate format allows us to directly unmarshall the HTTP response bodies from the VideoRepository
 * into a list of Video objects.
 * 
 * @author jules
 *
 */
public class ResourcesMapper extends ObjectMapper {

	// This anonymous inner class will handle conversion of the Spring Data Rest
	// Resources objects into JSON. Resources are objects that Spring Data Rest
	// creates with the Videos it obtains from your VideoRepository
	@SuppressWarnings("rawtypes")
	private JsonSerializer<Resources> serializer = new JsonSerializer<Resources>() {

		// We are going to register this class to handle all instances of type
		// Resources
		@Override
		public Class<Resources> handledType() {
			return Resources.class;
		}

		@Override
		public void serialize(Resources value, JsonGenerator jgen,
				SerializerProvider provider) throws IOException,
				JsonProcessingException {
			// Extracted the actual data inside of the Resources object
			// that we care about (e.g., the list of Video objects)
			Object content = value.getContent();
			// Instead of all of the Resources member variables, etc.
			// Just mashall the actual content (Videos) into the JSON
			JsonSerializer<Object> s = provider.findValueSerializer(
					content.getClass(), null);
			s.serialize(content, jgen, provider);
		}
	};
	
	
	
	@SuppressWarnings("rawtypes")
	private class CheckInDeserializer extends JsonDeserializer<CheckIn> {
		 
	    @Override
	    public CheckIn deserialize(JsonParser jp, DeserializationContext ctxt)
	      throws IOException, JsonProcessingException {
	        JsonNode node = jp.getCodec().readTree(jp);
	        return new CheckIn(
	        		(Integer) ((IntNode) node.get("id")).numberValue(),	        		
	        		(Integer) ((IntNode) node.get("patientRecordId")).numberValue(),					
	        		node.get("checkinDatetime").asText(),
	        		(Integer) ((IntNode) node.get("painLevel")).numberValue(),
	        		(Integer) ((IntNode) node.get("medicationTaken")).numberValue(),
	        		node.get("medicationDatetime").asText(),
	        		node.get("medicationAnswers").asText(),
	        		(Integer) ((IntNode) node.get("stoppedEatingLevel")).numberValue(),	        		
	        		//node.get("serverTimestamp").asText(),
	        		"",
	        		(Integer) ((IntNode) node.get("syncAction")).numberValue(),	        		
	        		(Integer) ((IntNode) node.get("synced")).numberValue()
					);
	    }
	
	}
	
	@SuppressWarnings("rawtypes")
	private class PatientMedicationDeserializer extends JsonDeserializer<PatientMedication> {
		 
	    @Override
	    public PatientMedication deserialize(JsonParser jp, DeserializationContext ctxt)
	      throws IOException, JsonProcessingException {
	        JsonNode node = jp.getCodec().readTree(jp);
	        return new PatientMedication(
	        		(Integer) ((IntNode) node.get("id")).numberValue(),	   
	        		(Integer) ((IntNode) node.get("patientRecordId")).numberValue(),		
	        		(Integer) ((IntNode) node.get("patientMedicationId")).numberValue(),
	        		node.get("patientMedicationFrom").asText(),
	        		node.get("patientMedicationTo").asText(),
	        		//node.get("serverTimestamp").asText(),
	        		"",
	        		(Integer) ((IntNode) node.get("syncAction")).numberValue(),	        		
	        		(Integer) ((IntNode) node.get("synced")).numberValue()
					);
	    }
	}
	
	
	@SuppressWarnings("rawtypes")
	private class PatientDeserializer extends JsonDeserializer<Patient> {
		 
	    @Override
	    public Patient deserialize(JsonParser jp, DeserializationContext ctxt)
	      throws IOException, JsonProcessingException {
	        JsonNode node = jp.getCodec().readTree(jp);
	        return new Patient(
	        		(Integer) ((IntNode) node.get("id")).numberValue(),	   
	        		(Integer) ((IntNode) node.get("recordId")).numberValue(),
	        		(Integer) ((IntNode) node.get("doctorId")).numberValue(),
	        		node.get("patientName").asText(),
	        		node.get("patientLastName").asText(),
	        		node.get("patientBirthday").asText(),
	        		(Integer) ((IntNode) node.get("severePainMinutes")).numberValue(),	     
	        		(Integer) ((IntNode) node.get("moderateToSeverePainMinutes")).numberValue(),
	        		(Integer) ((IntNode) node.get("noEatMinutes")).numberValue(),
	        		(Integer) ((IntNode) node.get("painLevel")).numberValue(),
	        		(Integer) ((IntNode) node.get("stoppedEatingLevel")).numberValue(),
	        		node.get("lastCheckinDatetime").asText(),
	        		node.get("clientLastSyncTimestamp").asText(),
	        		//node.get("serverTimestamp").asText(),
	        		"",
	        		(Integer) ((IntNode) node.get("syncAction")).numberValue(),	        		
	        		(Integer) ((IntNode) node.get("synced")).numberValue()
					);
	    }
	}
	
	
	
	
	// Create an ObjectMapper and tell it to use our customer serializer
	// to convert Resources objects into JSON
	public ResourcesMapper() {
		SimpleModule module = new SimpleModule();
		module.addSerializer(serializer);
		module.addDeserializer(CheckIn.class, new CheckInDeserializer());
		module.addDeserializer(PatientMedication.class, new PatientMedicationDeserializer());
		module.addDeserializer(Patient.class, new PatientDeserializer());
		registerModule(module);
	}

}
