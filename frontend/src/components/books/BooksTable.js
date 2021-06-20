import React from "react";

import Table from "react-bootstrap/Table";
import Button from "react-bootstrap/Button";
import BookItem from "./BookItem";

const BooksTable = ({books, deleteBook, updateBook}) => {
    return (
        <Table striped bordered hover size="sm">
            <thead>
            <tr>
                <th>#</th>
                <th>isbn</th>
                <th>Title</th>
                <th>Description</th>
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
                    <td>{book.title}</td>
                    <td>{book.description}</td>
                    <td>{book.numPages}</td>
                    <td>{book.publisher}</td>
                    <td>{book.language}</td>
                    <td>
                        <Button variant="secondary" onClick={() => updateBook(book.id)}>Edit</Button>
                        <Button variant="danger" onClick={() => deleteBook(book.id)}>Delete</Button>
                    </td>
                </tr>
            ))}
            </tbody>
        </Table>
    )
}

export default BooksTable