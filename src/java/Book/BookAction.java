/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Book;

import Book.Book.Book;
import User.User.User;
import User.UserService;
import com.opensymphony.xwork2.ActionSupport;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import User.WebService.IWebservice;
import java.io.BufferedReader;
import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Administrator
 */
public class BookAction extends ActionSupport {
    private int p=0;
    private BookService service;
    private UserService userservice;
    private SessionFactory factory;
    public void setFactory(SessionFactory factory){this.factory = factory;}

    public BookAction() { p=0;
    }

    public void setService(BookService service) {
        this.service = service;
    }

    public void setUserservice(UserService userservice) {
        this.userservice = userservice;
    }

    public String Search_Books() {
        String type = ServletActionContext.getRequest().getParameter("type");
        String search = ServletActionContext.getRequest().getParameter("search");
        ApplicationContext ctx = new FileSystemXmlApplicationContext("classpath:User/WebService/cxf.xml");
        IWebservice w_service = (IWebservice) ctx.getBean("WebService_Publish");
        List<Book>books =(List<Book>)w_service.Query_books(type, search);
        //ServletActionContext.getRequest().getSession().setAttribute("logintime", str);
        //System.out.println(str);
        //List<Book> books = service.SearchBook(type, search);
        ServletActionContext.getRequest().setAttribute("Book_Search", books);
        String from = ServletActionContext.getRequest().getParameter("from");
        //System.out.println(books.get(0).getBookname());
        return from;
    }

    public void SearchBooksAjax() throws IOException {
        String type = "bookid";
        HttpServletRequest request = ServletActionContext.getRequest();
        String ajax_bookname = request.getParameter("bookid");
        List<Book> books = service.SearchBook(type, ajax_bookname);
        HttpServletResponse response = ServletActionContext.getResponse();

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        if (books.size() == 0) {
            response.getWriter().write("");
        } else {
            Book b = books.get(0);
            String str = "id是" + b.getBookid() + " 书名是" + b.getBookname() + " 作者是" + b.getAuthor() + " 价格是" + b.getPrice() + "元 类别是" + b.getCategory();
            response.getWriter().write(str);
        }
        return;
    }

    public String Add_Book() {
        Book book = new Book();
        book.setBookname(ServletActionContext.getRequest().getParameter("bookname"));
        book.setAuthor(ServletActionContext.getRequest().getParameter("author"));
        int price = parseInt(ServletActionContext.getRequest().getParameter("price"));
        book.setCategory(ServletActionContext.getRequest().getParameter("category"));
        book.setPrice(price);
        p=0;
        if (service.AddBook(book)) {
            return SUCCESS;
        }
        return ERROR;
    }

    public String Delete_Book() {
        Book book = new Book();
        book.setBookid(Integer.parseInt(String.valueOf(ServletActionContext.getRequest().getParameter("bookid"))));
        book.setBookname(ServletActionContext.getRequest().getParameter("bookname"));
        p=0;
        if (service.DeleteBook(book)) {
            return SUCCESS;
        }
        return ERROR;
    }

    public String Jump_Books() {
        String login_flag = (String) ServletActionContext.getRequest().getAttribute("login_flag");
        if (login_flag!=null && login_flag.equals("false"))
            return "login";
        String userid = ServletActionContext.getRequest().getParameter("userid");
        int id = parseInt(userid);
        User user = userservice.getUserById(id);
        ServletActionContext.getRequest().getSession().setAttribute("User", user);
        return SUCCESS;
    }
    
    public void init() {
    File file=new File("D:\\lucene\\index");
    if(file.isDirectory())
    { //否则如果它是一个目录
        File files[] = file.listFiles(); //声明目录下所有的文件 files[];
        int length=files.length;
        for(int i=0;i<length;i++)
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
        p=1;
    }
    catch(IOException e)
    {
        System.out.println("error");
        return;
    }
  }
 
 public String search() throws Exception {
    String type = ServletActionContext.getRequest().getParameter("type");
    String queryString = ServletActionContext.getRequest().getParameter("search");
    if(queryString==""||queryString==null) return SUCCESS;
    if(p==0)
        init();
    String index = "D:\\lucene\\index";
    String field = type;
    String queries = null;
    int repeat = 0;
    boolean raw = false;
    
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
      return SUCCESS;
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
    List<Book> list=new ArrayList<Book>();  
      for (int i = start; i < end; i++) 
      {
        Document doc = searcher.doc(hits[i].doc);
        Book temp=new Book();
       Integer id = Integer.valueOf(doc.get("id"));
       String author=doc.get("author");
       Double price=Double.valueOf(doc.get("price"));
       String category=doc.get("category");
       String bookname=doc.get("bookname");
       temp.setBookid(id);
       temp.setAuthor(author);
       temp.setBookname(bookname);
       temp.setPrice(price);
       temp.setCategory(category);
       System.out.println(id+author+price+category+bookname);
       list.add(temp);
      }
       ServletActionContext.getRequest().setAttribute("Book_Search", list);
    }
 
}
