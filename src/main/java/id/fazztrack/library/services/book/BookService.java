package id.fazztrack.library.services.book;

import id.fazztrack.library.payloads.request.BookRequest;
import id.fazztrack.library.payloads.response.ResponseData;

public interface BookService {
  // Kerangka methods CRUD
  // Create Book
  ResponseData createBookService(BookRequest request);

  // Read Books
  ResponseData getBooksService(Boolean status);

  ResponseData getBookByIdService(Long idBook);

  // Update
  ResponseData updateBookByIdService(Long idBook, BookRequest request);

  // Delete
  ResponseData deleteBookService(Long idBook);
}
