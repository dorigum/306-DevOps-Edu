import styled from 'styled-components'
import VisualSection from '../components/main/VisualSection'
import ProductSection from '../components/main/ProductSection'
import ConstructionCase from '../components/main/ConstructionCase'
import BBSSection from '../components/main/BBSSection'

function Home() {
  return (
    <Main>
      <VisualSection />
      <ProductSection />
      <ConstructionCase />
      <BBSSection />
    </Main>
  )
}

export default Home

const Main = styled.main`
  width: 100%;
  padding-top: 90px;
`
