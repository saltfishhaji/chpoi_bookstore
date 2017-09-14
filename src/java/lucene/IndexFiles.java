package lucene;

/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
//import org.hibernate.Query;
import Book.Book.Book;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;

/** Index all text files under a directory.
 * <p>
 * This is a command-line application demonstrating simple Lucene indexing.
 * Run it with no command-line arguments for usage information.
 */
public class IndexFiles {
  private SessionFactory factory;
  private IndexFiles() {}

  /** Index all text files under a directory. */
 public void init() {
    File file=new File("D:\\lucene\\index");
    if(file.isDirectory())
    { //否则如果它是一个目录
        File files[] = file.listFiles(); //声明目录下所有的文件 files[];
        for(int i=0;i<files.length;i++)
        { //遍历目录下所有的文件
            files[i].delete(); //把每个文件 用这个方法进行迭代
        }
    }
    String indexPath = "D:\\lucene\\index";
    //String docsPath = "D:\\lucene\\data";
    boolean create = true;
    try
    {
        Session session=factory.openSession();
        String hql = "from Book";
        org.hibernate.Query query = session.createQuery(hql);
        List<Book> list=query.list();
        Directory indexDir = FSDirectory.open(Paths.get("D:\\lucene\\index"));
        Analyzer luceneAnalyzer = new StandardAnalyzer(); //新建一个分词器实例  
        IndexWriterConfig config = new IndexWriterConfig(luceneAnalyzer);  
        IndexWriter indexWriter = new IndexWriter(indexDir,config);
        for(int i=0;i<list.size();i++)
        {
            Document document = new Document();
            Field field1 = new StringField("id",list.get(i).getBookid().toString(),Field.Store.YES);  
            Field field2 = new StringField("bookname",list.get(i).getBookname(),Field.Store.YES);  
            Field field3 = new StringField("author",list.get(i).getAuthor(),Field.Store.YES);     
            Field field4 = new StringField("category",list.get(i).getCategory(),Field.Store.YES);  
            Field field5 = new StringField("price", Double.toString(list.get(i).getPrice()),Field.Store.YES);  
            document.add(field1);  
            document.add(field2);  
            document.add(field3);  
            document.add(field4);
            document.add(field5);
            indexWriter.addDocument(document);
        }
        indexWriter.close();
    }
    catch(IOException e)
    {
        System.out.println("error");
        return;
    }
  }
 
 public void search() throws Exception {
    init();
    String type="bookname";
    String index = "D:\\lucene\\index";
    String field = type;
    String queries = null;
    int repeat = 0;
    boolean raw = false;
    
    String queryString = "mmp";
    
    int hitsPerPage = 10;
    IndexReader reader = DirectoryReader.open(FSDirectory.open(Paths.get(index)));
    IndexSearcher searcher = new IndexSearcher(reader);
    Analyzer analyzer = new StandardAnalyzer();

    BufferedReader in = null;
    QueryParser parser = new QueryParser(field, analyzer);

      Query query = parser.parse(queryString);
      
      System.out.println("Searching for: " + query.toString(field));
      searcher.search(query, null, 10);
      doSearch(in, searcher, query, hitsPerPage, raw, queries == null && queryString == null);    
      reader.close();
  }

  public void doSearch(BufferedReader in, IndexSearcher searcher, Query query, 
                                     int hitsPerPage, boolean raw, boolean interactive) throws IOException {
 
    // Collect enough docs to show 5 pages
    TopDocs results = searcher.search(query, 5 * hitsPerPage);
    ScoreDoc[] hits = results.scoreDocs;
    
    int numTotalHits = results.totalHits;
    System.out.println(numTotalHits + " total matching documents");

    int start = 0;
    int end = Math.min(numTotalHits, hitsPerPage);
      
      for (int i = start; i < end; i++) 
      {
        Document doc = searcher.doc(hits[i].doc);
       String id = doc.get("id");
       String author=doc.get("author");
       String price=doc.get("price");
       String category=doc.get("category");
       String bookname=doc.get("bookname");
        System.out.println(id+author+price+category+bookname);
      }
    }
}