import Button from "react-bootstrap/Button";
import CollectionForm from "../books/BookForm";
import CollectionsTable from "./CollectionsTable";
import React, {useEffect, useState} from "react";
import axios from "axios";

const CollectionPage = () => {
    const [collections, setCollections] = useState([])
    const [showCollectionForm, setShowCollectionForm] = useState(false)
    const [updating, setUpdating] = useState(false)
    const [updatingCollection, setUpdatingCollection] = useState({})

    const hasCollections = collections.length > 0

    useEffect(() => {
        axios.get('http://localhost:8080/collections')
            .then((response) => {
                console.log(response.data)
                setCollections(response.data)
            })
            .catch((err) => {
                console.log(err)
            })
    }, [])

    const handleAddCollection = (collection) => {
        setShowCollectionForm(false)

        axios.post('http://localhost:8080/collections', collection)
            .then((res) => setCollections([...collections, res.data]))
            .catch((err) => console.log(err))
    }

    const handleDeleteCollection = (id) => {
        axios.delete(`http://localhost:8080/collections/${id}`)
            .then(() => setCollections(collections.filter(p => p.id !== id)))
            .catch((err) => console.log(err))
    }

    const handleOpenAddCollectionForm = () => {
        setShowCollectionForm(true)
    }

    const handleOpenUpdateCollectionForm = (id) => {
        setUpdatingCollection(collections.find(p => p.id === id))
        setUpdating(true)
        setShowCollectionForm(true)
    }

    const handleCloseCollectionForm = () => {
        setShowCollectionForm(false)
        setUpdating(false)
        setUpdatingCollection({})
    }

    const handleUpdateCollection = (collection) => {
        setShowCollectionForm(false)
        setUpdating(false)

        axios.patch(`http://localhost:8080/collections/${collection.id}`, collection)
            .then((res) =>
                setCollections([...collections.filter(p => p.id !== collection.id), res.data]))
            .catch((err) => console.log(err))
    }
    return (
        <div>
            <h3>Collection</h3>

            <Button variant="primary" onClick={handleOpenAddCollectionForm}>Add</Button>

            <CollectionForm
                show={showCollectionForm}
                close={handleCloseCollectionForm}
                addBook={handleAddCollection}
                updating={updating}
                updateBook={handleUpdateCollection}
                currentBook={updatingCollection}
            />

            {hasCollections &&
            <CollectionsTable
                collections={collections}
                deleteCollections={handleDeleteCollection}
                updateCollection={handleOpenUpdateCollectionForm}
            />
            }
        </div>
    )
}

export default CollectionPage