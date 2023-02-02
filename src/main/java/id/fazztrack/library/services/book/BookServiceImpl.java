package id.fazztrack.library.services.book;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import id.fazztrack.library.models.Book;
import id.fazztrack.library.payloads.request.BookRequest;
import id.fazztrack.library.payloads.response.ResponseData;
import id.fazztrack.library.repositories.BookRepository;

@Service
public class BookServiceImpl implements BookService {
  // Instance object
  @Autowired
  private BookRepository bookRepository;

  // private Book book = new Book();

  @Override
  public ResponseData createBookService(BookRequest request) {
    // TODO Auto-generated method stub
    Book book = new Book();
    // Validasi null input
    if (request.getJudul() != null) {
      book.setTitle(request.getJudul());
    }
    if (request.getKategori() != null) {
      book.setCategory(request.getKategori());
    }
    if (request.getPenerbit() != null) {
      book.setPublisher(request.getPenerbit());
    }
    if (request.getTahun() != null) {
      book.setYear(request.getTahun());
    }
    if (request.getPengarang() != null) {
      book.setAuthor(request.getPengarang());
    }

    // Save to db
    bookRepository.save(book);

    // Object response data
    ResponseData responseData = new ResponseData(HttpStatus.CREATED.value(), "Success", book);
    return responseData;
  }

  @Override
  public ResponseData getBooksService(Boolean status) {
    // TODO Auto-generated method stub
    List<Book> books;
    if (status == null) {
      books = bookRepository.findAll();
    } else {
      books = bookRepository.findByIsDeleted(status);
    }
    ResponseData responseData = new ResponseData(HttpStatus.OK.value(), "Success", books);
    return responseData;
  }

  @Override
  public ResponseData getBookByIdService(Long idBook) {
    // TODO Auto-generated method stub
    // Find book
    Optional<Book> bookFind = bookRepository.findById(idBook);
    ResponseData responseData;
    if (bookFind.isPresent()) {
      responseData = new ResponseData(HttpStatus.OK.value(), "Success", bookFind.get());
    } else {
      responseData = new ResponseData(HttpStatus.NOT_FOUND.value(), "Not Found", null);
    }
    return responseData;
  }

  @Override
  public ResponseData updateBookByIdService(Long idBook, BookRequest request) {
    // TODO Auto-generated method stub
    // Find book
    Optional<Book> bookFind = bookRepository.findById(idBook);
    ResponseData responseData;

    // Validate book
    if (bookFind.isPresent()) {
      // Update book
      Book book = bookFind.get();
      if (request.getJudul() != null) {
        book.setTitle(request.getJudul());
      }
      if (request.getKategori() != null) {
        book.setCategory(request.getKategori());
      }
      if (request.getPenerbit() != null) {
        book.setPublisher(request.getPenerbit());
      }
      if (request.getTahun() != null) {
        book.setYear(request.getTahun());
      }
      if (request.getPengarang() != null) {
        book.setAuthor(request.getPengarang());
      }

      // save to db
      bookRepository.save(book);

      responseData = new ResponseData(200, "Success", book);
    } else {
      responseData = new ResponseData(404, "Not Found", null);
    }
    return responseData;
  }

  @Override
  public ResponseData deleteBookService(Long idBook) {
    // TODO Auto-generated method stub
    // Find book
    Optional<Book> bookFind = bookRepository.findById(idBook);
    ResponseData responseData;
    // Validasi book present or not
    if (bookFind.isPresent()) {
      Book book = bookFind.get();
      book.setIsDeleted(true);

      // save
      bookRepository.save(book);

      responseData = new ResponseData(200, "Success", null);
    } else {
      responseData = new ResponseData(404, "Not Found", null);
    }

    return responseData;
  }

}
