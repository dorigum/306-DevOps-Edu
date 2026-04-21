import { Swiper, SwiperSlide } from 'swiper/react'
import { Navigation, Pagination } from 'swiper/modules'
import styled from 'styled-components'
import 'swiper/css'
import 'swiper/css/navigation'
import 'swiper/css/pagination'

const slides = [
  { bg: 'bg1' },
  { bg: 'bg2' },
  { bg: 'bg3' },
  { bg: 'bg4' },
  { bg: 'bg5' },
]

function VisualSection() {
  return (
    <Visual>
      <SwiperWrap
        modules={[Navigation, Pagination]}
        navigation={{ nextEl: '.nbt1', prevEl: '.pbt1' }}
        pagination={{ el: '.swiper-pagination', clickable: true }}
        loop={true}
      >
        {slides.map((slide, idx) => (
          <SwiperSlide key={idx} className={slide.bg}>
            <div className="txt">
              <div className="slogun">
                <p className="rale">new dream</p>
                <strong className="rale-exbold">we build</strong>
              </div>
              <div className="desc">
                <p>최고의 제품과 최고의 서비스로 <br />고객만족을 실현합니다.</p>
              </div>
            </div>
          </SwiperSlide>
        ))}
        <NavBtn className="nbt1 swiper-button-next" />
        <NavBtn className="pbt1 swiper-button-prev" $isPrev />
        <div className="swiper-pagination" />
      </SwiperWrap>
    </Visual>
  )
}

export default VisualSection

const Visual = styled.div`
  width: 100%;
  position: relative;
`

const SwiperWrap = styled(Swiper)`
  width: 100%;
  height: 100%;

  .swiper-slide {
    height: 750px;
    background-repeat: no-repeat;
    background-size: cover;
    background-position: center center;
    text-align: center;
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
  }

  .bg1 { background-image: url('/img/visual01.jpg'); }
  .bg2 { background-color: #ccc; }
  .bg3 { background-color: #aac; }
  .bg4 { background-color: #fa3; }
  .bg5 { background-color: #7ac; }

  .txt {
    display: flex;
    flex-direction: column;
    align-items: center;
  }

  .slogun {
    padding: 30px 50px 0;
    color: #f37339;
    text-transform: uppercase;
    line-height: 0.9;
    position: relative;
    border: 6px solid #f37339;
    border-bottom: 0;
    text-shadow: 1px 1px 1px rgba(119,34,34,.5);

    &::after {
      position: absolute;
      bottom: -6px;
      left: -6px;
      width: 50px;
      height: 6px;
      background: #f37339;
      content: '';
      display: block;
    }

    &::before {
      position: absolute;
      bottom: -6px;
      right: -6px;
      width: 50px;
      height: 6px;
      background: #f37339;
      content: '';
      display: block;
    }

    p { font-size: 50px; }
    strong { display: block; font-size: 85px; }
  }

  .desc {
    color: #fff;
    font-size: 24px;
    line-height: 1.3;
    padding: 40px 0;
  }

  .swiper-button-prev,
  .swiper-button-next {
    width: 58px;
    height: 57px;
    margin-top: -28.5px;
    background-repeat: no-repeat;
    background-size: contain;
    background-position: center center;
    color: transparent;

    &::after { display: none; }
  }

  .swiper-button-prev,
  .pbt1 {
    background-image: url('/img/visual_prev.png');
    left: 45px;
  }

  .swiper-button-next,
  .nbt1 {
    background-image: url('/img/visual_next.png');
    right: 45px;
  }

  .swiper-pagination-bullet {
    width: 36px;
    height: 4px;
    background: #fff;
    border-radius: 0;
    opacity: 1;
  }

  .swiper-pagination-bullet-active {
    height: 6px;
  }
`

const NavBtn = styled.div``
