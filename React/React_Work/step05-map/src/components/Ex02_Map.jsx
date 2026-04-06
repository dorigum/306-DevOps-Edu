import React from 'react'

function Ex02_Map() {
    const products =[
        {id: 1, name: "짬뽕", price: 8000},
        {id: 2, name: "짜장면", price: 7500},
        {id: 3, name: "탕수육", price: 35000},
        {id: 4, name: "짬짜면", price: 40000},
        {id: 5, name: "깐풍기", price: 12000},
    ];

  return (
    <ul>
    {
        products.map((product) => <li>{product.id} / {product.name} / {product.price}<br/></li>)
    }
    </ul>
  )
}

export default Ex02_Map