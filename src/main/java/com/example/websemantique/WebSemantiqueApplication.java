package com.example.websemantique;

import org.openrdf.query.QueryLanguage;
import org.openrdf.query.TupleQuery;
import org.openrdf.query.TupleQueryResultHandler;
import org.openrdf.query.resultio.sparqlxml.SPARQLResultsXMLWriter;
import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.sparql.SPARQLRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebSemantiqueApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebSemantiqueApplication.class, args);
//        String sparqlEndpoint = "https://localhost:8030/sparql";
//        Repository repo = new SPARQLRepository(sparqlEndpoint);
//        repo.initialize();
//
//        try (RepositoryConnection conn = repo.getConnection()) {
//            String queryString = "PREFIX ns: <http://www.semanticweb.org/ilyes/ontologies/2022/8/velo.tn#>\n" +
//                    "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
//                    "\n" +
//                    "SELECT ?Phone ?Email ?Name ?Address\n" +
//                    "WHERE {\n" +
//                    "?Association ns:Phone ?Phone ;\n" +
//                    "             ns:Email ?Email ;\n" +
//                    "             ns:Name ?Name ;\n" +
//                    "             ns:Address ?Address .\n" +
//                    "}\n" +
//                    "\n";
//
//            TupleQuery tupleQuery = conn.prepareTupleQuery(QueryLanguage.SPARQL, queryString);
//
//            TupleQueryResultHandler jsonWriter = new SPARQLResultsXMLWriter(System.out);
//            //tupleQuery.evaluate(jsonWriter);
     //   }
    }

}
