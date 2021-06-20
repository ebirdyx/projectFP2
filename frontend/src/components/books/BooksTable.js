import React from "react";

import Table from "react-bootstrap/Table";
import Button from "react-bootstrap/Button";

const BooksTable = ({books, deleteBook, updateBook, detailBook}) => {
    return (
      <div className="col-xl-12 justify-content-center">
          <Table striped bordered hover size="sm">
              <thead>
              <tr>
                  <th>#</th>
                  <th>isbn</th>
                  <th>Book cover</th>
                  <th>Title</th>
                  <th>Year of publication</th>
                  <th>Number of pages</th>
                  <th>Publisher</th>
                  <th>Language</th>
                  <th>Actions</th>
              </tr>
              </thead>
              <tbody>

              {books.map(book => (
                <tr key={book.id}>
                    <td>{book.id}</td>
                    <td>{book.isbn}</td>
                    <td><img src={book.imageUrl} width="80" height="80" /></td>
                    <td className="text-wrap">{book.title}</td>
                    <td>{book.yearOfPublication}</td>
                    <td>{book.numPages}</td>
                    <td>{book.publisher}</td>
                    <td>{book.language}</td>
                    <td>
                        <Button className="mr-4" variant="warning" onClick={() => detailBook(book)}>Details</Button>
                        <Button className="mr-4" variant="secondary" onClick={() => updateBook(book.id)}>Edit</Button>
                        <Button variant="danger" onClick={() => deleteBook(book.id)}>Delete</Button>
                    </td>
                </tr>
              ))}
              </tbody>
          </Table>
      </div>
    )
}

export default BooksTable