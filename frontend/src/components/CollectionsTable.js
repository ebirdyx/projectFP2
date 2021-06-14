import React from "react";

import Table from "react-bootstrap/Table";
import Button from "react-bootstrap/Button";

const CollectionsTable = ({collections, deleteCollection, updateCollection}) => {
    return (
        <Table striped bordered hover size="sm">
            <thead>
            <tr>
                <th>#</th>
                <th>Name</th>
                <th>Description</th>
            </tr>
            </thead>
            <tbody>

            {collections.map(collection => (
                <tr key={collection.id}>
                    <td>{collection.id}</td>
                    <td>{collection.name}</td>
                    <td>{collection.description}</td>

                    <td>
                        <Button variant="secondary" onClick={() => updateCollection(collection.id)}>Edit</Button>
                        <Button variant="danger" onClick={() => deleteCollection(collection.id)}>Delete</Button>
                    </td>
                </tr>
            ))}
            </tbody>
        </Table>
    )
}

export default CollectionsTable