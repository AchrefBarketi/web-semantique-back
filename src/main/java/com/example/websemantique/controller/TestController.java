package com.example.websemantique.controller;

import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Model;
import org.json.JSONException;
import org.json.JSONObject;
import org.openrdf.query.QueryLanguage;
import org.openrdf.query.TupleQuery;
import org.openrdf.query.TupleQueryResultHandler;
import org.openrdf.query.resultio.sparqlxml.SPARQLResultsXMLWriter;
import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.sparql.SPARQLRepository;
import org.springframework.web.bind.annotation.*;
import tools.JenaEngine;

//import javax.ws.rs.Produces;
import javax.ws.rs.Produces;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class TestController {

    @ResponseBody
    @GetMapping("/sparql")
  //  @Produces("application/json")
    public ResultSet test() throws IOException {
//        String sparqlEndpoint = "http://localhost:8030/api/sparql";
//        Repository repo = new SPARQLRepository(sparqlEndpoint);
//        repo.initialize();
//        RepositoryConnection conn = repo.getConnection();
//
//        // try (RepositoryConnection conn = repo.getConnection()) {
//        String queryString = "PREFIX ns: <http://www.semanticweb.org/ilyes/ontologies/2022/8/velo.tn#>\n" +
//                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
//                "\n" +
//                "SELECT ?Phone ?Email ?Name ?Address\n" +
//                "WHERE {\n" +
//                "?Association ns:Phone ?Phone ;\n" +
//                "             ns:Email ?Email ;\n" +
//                "             ns:Name ?Name ;\n" +
//                "             ns:Address ?Address .\n" +
//                "}\n" +
//                "\n";
//
//        TupleQuery tupleQuery = conn.prepareTupleQuery(QueryLanguage.SPARQL, queryString);
//
//        TupleQueryResultHandler jsonWriter = new SPARQLResultsXMLWriter(System.out);
//        tupleQuery.evaluate(jsonWriter);
//        //System.out.println(jsonWriter);
//        return tupleQuery;
        // } catch(Exception e){
        //      System.out.println(e);
        //   }
           Model model = JenaEngine.readModel("data/Velo.tn.owl");

      //  OutputStream a = JenaEngine.executeQueryFile(model,"data/query.txt");

     //   System.out.println(a);
//        try {
//            JSONObject jsonObject = new JSONObject("{"+a+"}");
//            return jsonObject;
//        }catch (JSONException err){
//            System.out.println(err.toString());
//        }
        return JenaEngine.executeQueryFile(model,"data/query.txt");


         //  return JenaEngine.executeQueryFile(model,"data/query.txt");

    }

//    @GetMapping("/sparql2")
//    public String test2() {
//
////        QueryExecution qexec = QueryExecutionFactory.sparqlService("http://localhost:8030/api/sparql2", "PREFIX ns: <http://www.semanticweb.org/ilyes/ontologies/2022/8/velo.tn#>\n" +
////                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
////                "\n" +
////                "SELECT ?Phone ?Email ?Name ?Address\n" +
////                "\n" +
////                "WHERE {\n" +
////                "?Association ns:Phone ?Phone ;\n" +
////                "             ns:Email ?Email ;\n" +
////                "             ns:Name ?Name ;\n" +
////                "             ns:Address ?Address .\n" +
////                "}\n" +
////                "\n");
//        Model model = JenaEngine.readModel("data/Velo.tn.owl");
//
//        ResultSet results = JenaEngine.executeQueryFile(model,"data/query.txt");
//
//// write to a ByteArrayOutputStream
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//
//        ResultSetFormatter.outputAsJSON(outputStream, results);
//
//// and turn that into a String
//        String json = new String(outputStream.toByteArray());
//
//        System.out.println(json);
////
////        Model model = JenaEngine.readModel("data/Velo.tn.owl");
////
////
////        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
////        ResultSetFormatter.outputAsJSON(outputStream, JenaEngine.executeQueryFile(model,"data/query.txt"));  // and turn that into a
////        String json = new String(outputStream.toByteArray());
//        return json;
////
//    }
}
