import Button from "react-bootstrap/Button";
import BookForm from "./BookForm";
import BooksTable from "./BooksTable";
import React, {useEffect, useState} from "react";
import axios from "axios";

const BookPage = () => {
    const [books, setBooks] = useState([])
    const [showBookForm, setShowBookForm] = useState(false)
    const [updating, setUpdating] = useState(false)
    const [updatingBook, setUpdatingBook] = useState({})

    const hasBooks = books.length > 0

    useEffect(() => {
        axios.get('http://localhost:8080/books')
            .then((response) => {
                console.log(response.data)
                setBooks(response.data)
            })
            .catch((err) => {
                console.log(err)
            })
    }, [])

    const handleAddBook = (book) => {
        setShowBookForm(false)

        axios.post('http://localhost:8080/books', book)
            .then((res) => setBooks([...books, res.data]))
            .catch((err) => console.log(err))
    }

    const handleDeleteBook = (id) => {
        axios.delete(`http://localhost:8080/books/${id}`)
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

        axios.patch(`http://localhost:8080/books/${book.id}`, book)
            .then((res) =>
                setBooks([...books.filter(p => p.id !== book.id), res.data]))
            .catch((err) => console.log(err))
    }
    return (
        <div>
            <h3>Books</h3>

            <Button variant="primary" onClick={handleOpenAddBookForm}>Add</Button>

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