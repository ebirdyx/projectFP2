import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form";
import BookForm from "./BookForm";
import BooksTable from "./BooksTable";
import React, {useEffect, useState} from "react";
import BookApi from "../../api/book";

const BookPage = () => {
    const [books, setBooks] = useState([])
    const [showBookForm, setShowBookForm] = useState(false)
    const [updating, setUpdating] = useState(false)
    const [updatingBook, setUpdatingBook] = useState({})

    const hasBooks = books.length > 0

    useEffect(() => {
        BookApi.get()
          .then(res => setBooks(res.data))
          .catch(err => console.log(err))
    }, [])

    const handleAddBook = (book) => {
        setShowBookForm(false)

        BookApi.create(book)
          .then(res => setBooks([...books, res.data]))
          .catch(err => console.log(err))
    }

    const handleDeleteBook = (id) => {
        BookApi.delete(id)
            .then(() => setBooks(books.filter(p => p.id !== id)))
            .catch((err) => console.log(err))
    }

    const handleOpenAddBookForm = () => {
        setShowBookForm(true)
    }

    const handleOpenUpdateBookForm = (id) => {
        setUpdatingBook(books.find(p => p.id === id))
        setUpdating(true)
        setShowBookForm(true)
    }

    const handleCloseBookForm = () => {
        setShowBookForm(false)
        setUpdating(false)
        setUpdatingBook({})
    }

    const handleUpdateBook = (book) => {
        setShowBookForm(false)
        setUpdating(false)

        BookApi.update(book.id, book)
            .then(res =>
              setBooks([...books.filter(p => p.id !== book.id), res.data]))
            .catch((err) => console.log(err))
    }

    return (
        <div>
            <div>
                <Form.Control
                  type="text"
                  placeholder="Search..."
                />

                <Button variant="primary" onClick={handleOpenAddBookForm}>Add</Button>
            </div>

            <BookForm
                show={showBookForm}
                close={handleCloseBookForm}
                addBook={handleAddBook}
                updating={updating}
                updateBook={handleUpdateBook}
                currentBook={updatingBook}
            />

            {hasBooks &&
            <BooksTable
                books={books}
                deleteBooks={handleDeleteBook}
                updateBook={handleOpenUpdateBookForm}
            />
            }
        </div>
    )
}

export default BookPage