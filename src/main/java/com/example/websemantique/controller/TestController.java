package com.example.websemantique.controller;

import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Model;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;
import tools.JenaEngine;

//import javax.ws.rs.Produces;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

@RestController
@RequestMapping("/ws")
public class TestController {


    @GetMapping("/subclass/all/{Class}")
    public String getAllSubClass(@PathVariable(value = "Class") String Class) throws UnsupportedEncodingException {

        String qexec = "PREFIX ns: <http://www.semanticweb.org/ilyes/ontologies/2022/8/velo.tn#>\n" +
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>" +
                "\n" +
                "SELECT ?subClass  WHERE {\n" +
                "        ?subClass rdfs:subClassOf ns:"+Class+" .\n" +
                "    }";


        Model model = JenaEngine.readModel("data/Velo.tn.owl");

        QueryExecution qe = QueryExecutionFactory.create(qexec, model);
        ResultSet results = qe.execSelect();


        // write to a ByteArrayOutputStream
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        ResultSetFormatter.outputAsJSON(outputStream, results);

        // and turn that into a String
        String json = new String(outputStream.toByteArray());

        JSONObject j = new JSONObject(json);
        System.out.println(j.getJSONObject("results").getJSONArray("bindings").isEmpty());

        Boolean isEmpty = j.getJSONObject("results").getJSONArray("bindings").isEmpty();



        return j.getJSONObject("results").getJSONArray("bindings").toString();

    }

    @GetMapping("/association/all/{type}")
    public String getAllAssociations(@PathVariable(value = "type") String type) throws UnsupportedEncodingException {

        String qexec = "PREFIX ns: <http://www.semanticweb.org/ilyes/ontologies/2022/8/velo.tn#>\n" +
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "\n" +
                "SELECT ?Type ?Phone ?Email ?Name ?Address\n" +
                "\n" +
                "WHERE {\n" +
                "?Association ns:Phone ?Phone ;\n" +
                "             ns:Email ?Email ;\n" +
                "             ns:Name ?Name ;\n" +
                "             ns:Address ?Address ;" +
                "             ns:Type ?Type ;\n" +
                "FILTER ( ( ?Type = '"+type+"' )  ) " +
                "}\n" +
                "\n";


        Model model = JenaEngine.readModel("data/Velo.tn.owl");

        QueryExecution qe = QueryExecutionFactory.create(qexec, model);
        ResultSet results = qe.execSelect();


        // write to a ByteArrayOutputStream
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        ResultSetFormatter.outputAsJSON(outputStream, results);

        // and turn that into a String
        String json = new String(outputStream.toByteArray());

        JSONObject j = new JSONObject(json);
        System.out.println(j.getJSONObject("results").getJSONArray("bindings"));

        JSONArray res = j.getJSONObject("results").getJSONArray("bindings");




        return j.getJSONObject("results").getJSONArray("bindings").toString();

    }

    @GetMapping("/bikes/all/{type}")
    public String getAllBikes(@PathVariable(value = "type") String type) {

        String qexec = "PREFIX ns: <http://www.semanticweb.org/ilyes/ontologies/2022/8/velo.tn#>\n" +
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "\n" +
                "\n" +
                "SELECT ?Type ?Code ?Model ?Disponibility ?Status ?PriceHour\n" +
                "WHERE {\n" +
                "?Bike ns:Code ?Code ;\n" +
                "             ns:Model ?Model ;\n" +
                "             ns:Disponibility ?Disponibility ;\n" +
                "             ns:Status ?Status ;\n" +
                "             ns:PriceHour ?PriceHour ;" +
                "             ns:Type ?Type .\n" +
                "FILTER ( ( ?Type = '"+type+"' )  ) " +
                "}";


        Model model = JenaEngine.readModel("data/Velo.tn.owl");

        QueryExecution qe = QueryExecutionFactory.create(qexec, model);
        ResultSet results = qe.execSelect();


        // write to a ByteArrayOutputStream
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        ResultSetFormatter.outputAsJSON(outputStream, results);

        // and turn that into a String
        String json = new String(outputStream.toByteArray());

        JSONObject j = new JSONObject(json);
        System.out.println(j.getJSONObject("results").getJSONArray("bindings"));

        JSONArray res = j.getJSONObject("results").getJSONArray("bindings");




        return j.getJSONObject("results").getJSONArray("bindings").toString();

    }

    @GetMapping("/bikes/all/{type}/{title}")
    public String getAllBikesByTitle(@PathVariable(value = "type") String type, @PathVariable(value = "title") String title) {

        String qexec = "PREFIX ns: <http://www.semanticweb.org/ilyes/ontologies/2022/8/velo.tn#>\n" +
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "\n" +
                "\n" +
                "SELECT ?Type ?Code ?Model ?Disponibility ?Status ?PriceHour\n" +
                "WHERE {\n" +
                "?Bike ns:Code ?Code ;\n" +
                "             ns:Model ?Model ;\n" +
                "             ns:Disponibility ?Disponibility ;\n" +
                "             ns:Status ?Status ;\n" +
                "             ns:PriceHour ?PriceHour ;" +
                "             ns:Type ?Type .\n" +
                "FILTER ( ( ?Title = '"+title+"' && ?Type = '"+type+"')  ) " +
                "}";

        Model model = JenaEngine.readModel("data/Velo.tn.owl");

        QueryExecution qe = QueryExecutionFactory.create(qexec, model);
        ResultSet results = qe.execSelect();

        // write to a ByteArrayOutputStream
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        ResultSetFormatter.outputAsJSON(outputStream, results);

        // and turn that into a String
        String json = new String(outputStream.toByteArray());

        JSONObject j = new JSONObject(json);
        System.out.println(j.getJSONObject("results").getJSONArray("bindings"));

        JSONArray res = j.getJSONObject("results").getJSONArray("bindings");


        return j.getJSONObject("results").getJSONArray("bindings").toString();

    }




    @GetMapping("/events/all/{type}")
    public String getAllEventsByType(@PathVariable(value = "type") String type) {

        String qexec = "PREFIX ns: <http://www.semanticweb.org/ilyes/ontologies/2022/8/velo.tn#>\n" +
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "\n" +
                "\n" +
                "SELECT ?Type ?Title ?Description ?Location ?EndDate ?StartDate\n" +
                "WHERE {\n" +
                "?Events ns:Title ?Title ;\n" +
                "             ns:Description ?Description ;\n" +
                "             ns:Location ?Location ;\n" +
                "             ns:EndDate ?EndDate ;\n" +
                "             ns:StartDate ?StartDate ;" +
                "             ns:Type ?Type .\n" +
                "FILTER ( ( ?Type = '"+type+"' )  ) " +
                "}";

        Model model = JenaEngine.readModel("data/Velo.tn.owl");

        QueryExecution qe = QueryExecutionFactory.create(qexec, model);
        ResultSet results = qe.execSelect();

        // write to a ByteArrayOutputStream
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        ResultSetFormatter.outputAsJSON(outputStream, results);

        // and turn that into a String
        String json = new String(outputStream.toByteArray());

        JSONObject j = new JSONObject(json);
        System.out.println(j.getJSONObject("results").getJSONArray("bindings"));

        JSONArray res = j.getJSONObject("results").getJSONArray("bindings");


        return j.getJSONObject("results").getJSONArray("bindings").toString();

    }

    @GetMapping("/events/all/{type}/{title}")
    public String getAllEventsByTitle(@PathVariable(value = "type") String type, @PathVariable(value = "title") String title) {

        String qexec = "PREFIX ns: <http://www.semanticweb.org/ilyes/ontologies/2022/8/velo.tn#>\n" +
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "\n" +
                "\n" +
                "SELECT ?Type ?Title ?Description ?Location ?EndDate ?StartDate\n" +
                "WHERE {\n" +
                "?Events ns:Title ?Title ;\n" +
                "             ns:Description ?Description ;\n" +
                "             ns:Location ?Location ;\n" +
                "             ns:EndDate ?EndDate ;\n" +
                "             ns:StartDate ?StartDate ;" +
                "             ns:Type ?Type .\n" +
                "FILTER ( ( ?Title = '"+title+"' && ?Type = '"+type+"')  ) " +
                "}";

        Model model = JenaEngine.readModel("data/Velo.tn.owl");

        QueryExecution qe = QueryExecutionFactory.create(qexec, model);
        ResultSet results = qe.execSelect();

        // write to a ByteArrayOutputStream
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        ResultSetFormatter.outputAsJSON(outputStream, results);

        // and turn that into a String
        String json = new String(outputStream.toByteArray());

        JSONObject j = new JSONObject(json);
        System.out.println(j.getJSONObject("results").getJSONArray("bindings"));

        JSONArray res = j.getJSONObject("results").getJSONArray("bindings");


        return j.getJSONObject("results").getJSONArray("bindings").toString();

    }

    @GetMapping("/events/all/search/{type}/{title}")
    public String getAllEventsByTitleSearch(@PathVariable(value = "type") String type, @PathVariable(value = "title") String title) {

        String qexec = "PREFIX ns: <http://www.semanticweb.org/ilyes/ontologies/2022/8/velo.tn#>\n" +
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "\n" +
                "\n" +
                "SELECT ?Type ?Title ?Description ?Location ?EndDate ?StartDate\n" +
                "WHERE {\n" +
                "?Events ns:Title ?Title ;\n" +
                "             ns:Description ?Description ;\n" +
                "             ns:Location ?Location ;\n" +
                "             ns:EndDate ?EndDate ;\n" +
                "             ns:StartDate ?StartDate ;" +
                "             ns:Type ?Type .\n" +
                "FILTER ( ( ?Type = '"+type+"' )  ) " +
                "FILTER CONTAINS ( lcase(?Title) , '"+title.toLowerCase()+"'  ) " +
                "}";

        Model model = JenaEngine.readModel("data/Velo.tn.owl");

        QueryExecution qe = QueryExecutionFactory.create(qexec, model);
        ResultSet results = qe.execSelect();

        // write to a ByteArrayOutputStream
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        ResultSetFormatter.outputAsJSON(outputStream, results);

        // and turn that into a String
        String json = new String(outputStream.toByteArray());

        JSONObject j = new JSONObject(json);
        System.out.println(j.getJSONObject("results").getJSONArray("bindings"));

        JSONArray res = j.getJSONObject("results").getJSONArray("bindings");


        return j.getJSONObject("results").getJSONArray("bindings").toString();

    }













    @GetMapping("/tours/all/{type}")
    public String getAllToursByType(@PathVariable(value = "type") String type) {

        String qexec = "PREFIX ns: <http://www.semanticweb.org/ilyes/ontologies/2022/8/velo.tn#>\n" +
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "\n" +
                "\n" +
                "SELECT ?Type ?Title ?Description ?Location ?Duration ?Date\n" +
                "WHERE {\n" +
                "?City ns:Title ?Title ;\n" +
                "             ns:Description ?Description ;\n" +
                "             ns:Location ?Location ;\n" +
                "             ns:Duration ?Duration ;\n" +
                "             ns:Date ?Date ;" +
                "             ns:Type ?Type .\n" +
                "FILTER ( ( ?Type = '"+type+"' )  ) " +
                "}";

        Model model = JenaEngine.readModel("data/Velo.tn.owl");

        QueryExecution qe = QueryExecutionFactory.create(qexec, model);
        ResultSet results = qe.execSelect();

        // write to a ByteArrayOutputStream
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        ResultSetFormatter.outputAsJSON(outputStream, results);

        // and turn that into a String
        String json = new String(outputStream.toByteArray());

        JSONObject j = new JSONObject(json);
        System.out.println(j.getJSONObject("results").getJSONArray("bindings"));

        JSONArray res = j.getJSONObject("results").getJSONArray("bindings");


        return j.getJSONObject("results").getJSONArray("bindings").toString();

    }


    @GetMapping("/tours/all/{type}/{title}")
    public String getAllToursByTitle(@PathVariable(value = "type") String type, @PathVariable(value = "title") String title) {

        String qexec = "PREFIX ns: <http://www.semanticweb.org/ilyes/ontologies/2022/8/velo.tn#>\n" +
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "\n" +
                "\n" +
                "SELECT ?Type ?Title ?Description ?Location ?Duration ?Date\n" +
                "WHERE {\n" +
                "?City ns:Title ?Title ;\n" +
                "             ns:Description ?Description ;\n" +
                "             ns:Location ?Location ;\n" +
                "             ns:Duration ?Duration ;\n" +
                "             ns:Date ?Date ;" +
                "             ns:Type ?Type .\n" +
                "FILTER ( ( ?Title = '"+title+"' && ?Type = '"+type+"')  ) " +
                "}";

        Model model = JenaEngine.readModel("data/Velo.tn.owl");

        QueryExecution qe = QueryExecutionFactory.create(qexec, model);
        ResultSet results = qe.execSelect();

        // write to a ByteArrayOutputStream
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        ResultSetFormatter.outputAsJSON(outputStream, results);

        // and turn that into a String
        String json = new String(outputStream.toByteArray());

        JSONObject j = new JSONObject(json);
        System.out.println(j.getJSONObject("results").getJSONArray("bindings"));

        JSONArray res = j.getJSONObject("results").getJSONArray("bindings");


        return j.getJSONObject("results").getJSONArray("bindings").toString();

    }



    @GetMapping("/tours/all/search/{type}/{title}")
    public String getAllToursByTitleSearch(@PathVariable(value = "type") String type, @PathVariable(value = "title") String title) {

        String qexec = "PREFIX ns: <http://www.semanticweb.org/ilyes/ontologies/2022/8/velo.tn#>\n" +
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "\n" +
                "\n" +
                "SELECT ?Type ?Title ?Description ?Location ?Duration ?Date\n" +
                "WHERE {\n" +
                "?City ns:Title ?Title ;\n" +
                "             ns:Description ?Description ;\n" +
                "             ns:Location ?Location ;\n" +
                "             ns:Duration ?Duration ;\n" +
                "             ns:Date ?Date ;" +
                "             ns:Type ?Type .\n" +
                "FILTER ( ( ?Type = '"+type+"' )  ) " +
                "FILTER CONTAINS ( lcase(?Title) , '"+title.toLowerCase()+"'  ) " +
                "}";

        Model model = JenaEngine.readModel("data/Velo.tn.owl");

        QueryExecution qe = QueryExecutionFactory.create(qexec, model);
        ResultSet results = qe.execSelect();

        // write to a ByteArrayOutputStream
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        ResultSetFormatter.outputAsJSON(outputStream, results);

        // and turn that into a String
        String json = new String(outputStream.toByteArray());

        JSONObject j = new JSONObject(json);
        System.out.println(j.getJSONObject("results").getJSONArray("bindings"));

        JSONArray res = j.getJSONObject("results").getJSONArray("bindings");


        return j.getJSONObject("results").getJSONArray("bindings").toString();

    }






}
