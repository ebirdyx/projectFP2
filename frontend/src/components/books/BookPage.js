import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form";
import BookForm from "./BookForm";
import BooksTable from "./BooksTable";
import React, {useEffect, useState} from "react";
import BookApi from "../../api/book";
import BookDetails from "./BookDetails";

const BookPage = () => {
    const [books, setBooks] = useState([])
    const [showBookForm, setShowBookForm] = useState(false)
    const [showBookDetails, setShowBookDetails] = useState(false)
    const [detailBook, setDetailBook] = useState({})
    const [updating, setUpdating] = useState(false)
    const [updatingBook, setUpdatingBook] = useState({})

    const hasBooks = books.length > 0

    useEffect(() => {
        BookApi.get()
          .then(res => setBooks(res.data))
          .catch(err => console.log(err))
    }, [])

    const handleAddBook = (book, content) => {
        setShowBookForm(false)

        BookApi.create(book)
          .then(res => setBooks([...books, res.data]))
          .catch(err => console.log(err))

        if (content)
          handleBookUpload(book.id, content)
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

    const handleBookUpload = (bookId, content) => {
      BookApi.upload(bookId, content)
        .then(_ => {
          let book = books.find(b => b.id === bookId)
          book.fileName = content.name
          setBooks([...books.filter(b => b.id !== bookId), book])
        })
        .catch(err => console.log(err))
    }

  const handleUpdateBook = (book, content) => {
        setShowBookForm(false)
        setUpdating(false)

        BookApi.update(book.id, book)
            .then(res =>
              setBooks([...books.filter(p => p.id !== book.id), res.data]))
            .catch((err) => console.log(err))

        if (content)
          handleBookUpload(book.id, content)
    }

    const handleOpenDetailsBook = (book) => {
        setDetailBook(book)
        setShowBookDetails(true)
    }

    const handleCloseDetailsBook = () => {
        setShowBookDetails(false)
        setDetailBook({})
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

            <BookDetails
              show={showBookDetails}
              close={handleCloseDetailsBook}
              book={detailBook}
            />

            {hasBooks &&
            <BooksTable
                books={books}
                deleteBooks={handleDeleteBook}
                updateBook={handleOpenUpdateBookForm}
                detailBook={handleOpenDetailsBook}
            />
            }
        </div>
    )
}

export default BookPage