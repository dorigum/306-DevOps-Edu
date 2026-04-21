import { createGlobalStyle } from 'styled-components'

const GlobalStyle = createGlobalStyle`
  /* Reset */
  * {
    box-sizing: border-box;
    text-size-adjust: none;
    -webkit-text-size-adjust: none;
  }

  html, body {
    height: 100%;
    margin: 0;
    padding: 0;
  }

  body, th, td, input, select, textarea, button {
    font-size: 13px;
    line-height: 1.5;
    font-family: 'Noto Sans KR', sans-serif;
    color: #666;
  }

  body, div, dl, dt, dd, ul, ol, li,
  h1, h2, h3, h4, h5, h6,
  pre, code, form, fieldset, legend, textarea, p, blockquote,
  th, td, input, select, textarea, button,
  header, nav, menu, section, article {
    margin: 0;
    padding: 0;
  }

  fieldset, img {
    border: 0 none;
  }

  dl, ul, ol, menu, li {
    list-style: none;
    vertical-align: middle;
  }

  blockquote, q {
    quotes: none;
  }

  input, textarea {
    padding: 0 5px;
  }

  input, select, textarea, button {
    vertical-align: middle;
  }

  input[type='text'], input[type='password'], input[type='submit'], input[type='search'] {
    -webkit-appearance: none;
  }

  button {
    border: 0 none;
    background-color: transparent;
    cursor: pointer;
  }

  a, a:link, a:visited, a:hover, a:active {
    color: inherit;
    text-decoration: none;
  }

  img {
    vertical-align: middle;
  }

  table {
    width: 100%;
    border-collapse: collapse;
  }

  /* Common Layout */
  .wrapper {
    width: 100%;
    position: relative;
    margin: 0 auto;
    min-height: 600px;
  }

  .size {
    width: 100%;
    max-width: 1280px;
    height: auto;
    margin: 0 auto;
    position: relative;
  }

  .inner {
    width: 100%;
    padding: 0 20px;
    box-sizing: border-box;
    position: relative;
  }

  .clear:after {
    clear: both;
    content: '';
    display: block;
  }

  .tb {
    display: table;
    height: 100%;
    width: 100%;
  }

  .tbc {
    display: table-cell;
    vertical-align: middle;
  }

  /* Swiper custom styles */
  .swiper-container {
    width: 100%;
    height: 100%;
  }

  /* Fonts */
  .rale { font-family: 'Raleway', sans-serif; font-weight: 400; }
  .rale-bold { font-family: 'Raleway', sans-serif; font-weight: 600; }
  .rale-exbold { font-family: 'Raleway', sans-serif; font-weight: 800; }
`

export default GlobalStyle
