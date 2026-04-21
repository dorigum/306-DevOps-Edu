import { Swiper, SwiperSlide } from 'swiper/react'
import { Navigation } from 'swiper/modules'
import styled from 'styled-components'
import 'swiper/css'
import 'swiper/css/navigation'

const products = [
  { className: 'pro01', icon: '/img/round_ico01.png', label: '벽면' },
  { className: 'pro02', icon: '/img/round_ico02.png', label: '바닥' },
  { className: 'pro03', icon: '/img/round_ico03.png', label: '도어' },
  { className: 'pro04', icon: '/img/round_ico04.png', label: '몰딩' },
  { className: 'pro05', icon: '/img/round_ico05.png', label: '시트' },
  { className: 'pro06', icon: '/img/round_ico06.png', label: '가구' },
]

function ProductSection() {
  return (
    <Section>
      <div className="size">
        <SectionTitle>
          <h3>product</h3>
          <p>지속적인 변화와 혁신으로 더 나은 세상을 만들어가겠습니다</p>
        </SectionTitle>
        <RoundWrap>
          <ProductSwiper
            modules={[Navigation]}
            navigation={{ nextEl: '.nbt2', prevEl: '.pbt2' }}
            slidesPerView={5}
            spaceBetween={30}
            loop={true}
          >
            {products.map((product, idx) => (
              <SwiperSlide key={idx}>
                <RoundLink href="#" className={product.className}>
                  <div className="round">
                    <Overlay>
                      <div className="tb">
                        <div className="tbc">
                          <p className="ico"><img src={product.icon} alt={product.label} /></p>
                          <p>{product.label}</p>
                        </div>
                      </div>
                    </Overlay>
                  </div>
                </RoundLink>
              </SwiperSlide>
            ))}
          </ProductSwiper>
          <PrevBtn className="pbt2 swiper-button-prev" />
          <NextBtn className="nbt2 swiper-button-next" />
        </RoundWrap>
      </div>
    </Section>
  )
}

export default ProductSection

const Section = styled.section`
  padding: 60px 0;
`

const SectionTitle = styled.div`
  text-align: center;

  h3 {
    font-family: 'Raleway', sans-serif;
    font-weight: 800;
    text-transform: uppercase;
    color: #f37339;
    font-size: 45px;
    letter-spacing: -1px;
  }

  p {
    color: #515155;
    font-size: 18px;
    font-weight: 400;
    letter-spacing: -0.5px;
  }
`

const RoundWrap = styled.div`
  margin-top: 40px;
  position: relative;
`

const ProductSwiper = styled(Swiper)`
  .swiper-slide {
    display: flex;
    justify-content: center;
    align-items: center;
    background: transparent;
  }
`

const RoundLink = styled.a`
  width: 236px;
  height: 236px;
  display: block;
  position: relative;
  border-radius: 50%;
  background-repeat: no-repeat;
  background-position: center center;
  background-size: cover;
  transition: all .4s;

  &.pro01 { background-image: url('/img/round_01.png'); }
  &.pro02 { background-image: url('/img/round_02.png'); }
  &.pro03 { background-image: url('/img/round_03.png'); }
  &.pro04 { background-image: url('/img/round_04.png'); }
  &.pro05 { background-image: url('/img/round_05.png'); }
  &.pro06 { background-image: url('/img/round_06.png'); }

  &:hover div {
    background: rgba(243,115,57,.8);
  }

  .round {
    width: 100%;
    height: 100%;
    border-radius: 50%;
    overflow: hidden;
  }
`

const Overlay = styled.div`
  position: absolute;
  width: 70%;
  height: 70%;
  left: 15%;
  top: 15%;
  border-radius: 80%;
  text-align: center;
  background: transparent;
  color: #fff;
  transition: all .4s;

  p { font-size: 15px; }
  .ico { margin-bottom: 5px; }
`

const PrevBtn = styled.div`
  width: 18px;
  height: 33px;
  position: absolute;
  top: 50%;
  margin-top: -16.5px;
  left: -38px;
  background: url('/img/prev_ico.png') no-repeat center center;
  z-index: 10;
  cursor: pointer;

  &::after { display: none; }
`

const NextBtn = styled.div`
  width: 18px;
  height: 33px;
  position: absolute;
  top: 50%;
  margin-top: -16.5px;
  right: -38px;
  background: url('/img/next_ico.png') no-repeat center center;
  z-index: 10;
  cursor: pointer;

  &::after { display: none; }
`
