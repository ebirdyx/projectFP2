import React from "react";
import './App.css';
import axios from "axios";
import {useEffect, useState} from "react";
import ProductsTable from "./components/ProductsTable";

import Button from "react-bootstrap/Button";
import ProductForm from "./components/ProductForm";

const App = () => {
  const [products, setProducts] = useState([])
  const [showProductForm, setShowProductForm] = useState(false)
  const [updating, setUpdating] = useState(false)
  const [updatingProduct, setUpdatingProduct] = useState({})

  const hasProducts = products.length > 0

  useEffect(() => {
    axios.get('http://localhost:8080/products')
      .then((response) => {
        setProducts(response.data)
      })
      .catch((err) => {
        console.log(err)
      })
  }, [])

  const handleAddProduct = (product) => {
    setShowProductForm(false)

    axios.post('http://localhost:8080/products', product)
      .then((res) => setProducts([...products, res.data]))
      .catch((err) => console.log(err))
  }

  const handleDeleteProduct = (id) => {
    axios.delete(`http://localhost:8080/products/${id}`)
      .then(() => setProducts(products.filter(p => p.id !== id)))
      .catch((err) => console.log(err))
  }

  const handleOpenAddProductForm = () => {
    setShowProductForm(true)
  }

  const handleOpenUpdateProductForm = (id) => {
    setUpdatingProduct(products.find(p => p.id === id))
    setUpdating(true)
    setShowProductForm(true)
  }

  const handleCloseProductForm = () => {
    setShowProductForm(false)
    setUpdating(false)
    setUpdatingProduct({})
  }

  const handleUpdateProduct = (product) => {
    setShowProductForm(false)
    setUpdating(false)
    
    axios.patch(`http://localhost:8080/products/${product.id}`, product)
      .then((res) =>
        setProducts([...products.filter(p => p.id !== product.id), res.data]))
      .catch((err) => console.log(err))
  }

  return (
    <div className="App">
      <h3>Products</h3>

      <Button variant="primary" onClick={handleOpenAddProductForm}>Add</Button>

      <ProductForm
        show={showProductForm}
        close={handleCloseProductForm}
        addProduct={handleAddProduct}
        updating={updating}
        updateProduct={handleUpdateProduct}
        currentProduct={updatingProduct}
      />

      {hasProducts &&
        <ProductsTable
          products={products}
          deleteProduct={handleDeleteProduct}
          updateProduct={handleOpenUpdateProductForm}
        />
      }
    </div>
  );
}

export default App;
