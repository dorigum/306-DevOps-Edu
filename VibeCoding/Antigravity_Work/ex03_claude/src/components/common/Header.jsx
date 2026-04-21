import { useState } from 'react'
import styled from 'styled-components'

const gnbItems = [
  { label: '회사소개', depth2: ['인사말', '연혁', '조직도', '오시는길'] },
  { label: '제품소개', depth2: ['벽면', '바닥', '도어', '몰딩', '시트', '가구'] },
  { label: '시공사례', depth2: ['시공실적', '시공갤러리'] },
  { label: '견적문의', depth2: ['견적문의'] },
  { label: '고객센터', depth2: ['성공소식', '보도자료'] },
]

function Header() {
  const [hoveredIndex, setHoveredIndex] = useState(null)
  const [allMenuOpen, setAllMenuOpen] = useState(false)

  const toggleAllMenu = () => setAllMenuOpen(prev => !prev)

  return (
    <HeaderWrap>
      <div className="size">
        <div className="inner clear">
          <Logo>
            <a href="/"><img src="/img/logo.png" alt="로고" /></a>
          </Logo>

          <Gnb>
            <ul className="clear">
              {gnbItems.map((item, idx) => (
                <GnbItem
                  key={idx}
                  onMouseEnter={() => setHoveredIndex(idx)}
                  onMouseLeave={() => setHoveredIndex(null)}
                >
                  <a href="">{item.label}</a>
                  <Depth2 $open={hoveredIndex === idx}>
                    {item.depth2.map((sub, sIdx) => (
                      <li key={sIdx}><a href="">{sub}</a></li>
                    ))}
                  </Depth2>
                </GnbItem>
              ))}
            </ul>
          </Gnb>

          <MenuBtn>
            <AllMenuBtn className={allMenuOpen ? 'on' : ''} onClick={toggleAllMenu}>
              전체메뉴
            </AllMenuBtn>
          </MenuBtn>
        </div>
      </div>

      <AllGnb $open={allMenuOpen}>
        <div className="size">
          <div className="inner clear">
            <AllMenus>
              {gnbItems.map((item, idx) => (
                <AllMenuGroup key={idx} className={allMenuOpen ? 'on' : ''}>
                  {item.depth2.map((sub, sIdx) => (
                    <li key={sIdx}><a href="">{sub}</a></li>
                  ))}
                </AllMenuGroup>
              ))}
            </AllMenus>
          </div>
        </div>
      </AllGnb>
    </HeaderWrap>
  )
}

export default Header

const HeaderWrap = styled.header`
  width: 100%;
  position: fixed;
  height: 90px;
  z-index: 100;
  background: #fff;
  border-bottom: 1px solid rgba(255,255,255,.2);
  box-sizing: border-box;

  .inner {
    padding-left: 120px;
    padding-right: 70px;
  }
`

const Logo = styled.h1`
  position: absolute;
  left: 0;
  top: 50%;
  margin-top: -25px;
  display: block;

  a {
    display: block;
    font-size: 0;
    padding: 10px 0;
  }
`

const Gnb = styled.nav`
  float: right;
  box-sizing: border-box;

  > ul {
    display: flex;
  }
`

const GnbItem = styled.li`
  height: 90px;
  padding: 20px 0;
  position: relative;
  box-sizing: border-box;

  > a {
    display: block;
    height: 50px;
    line-height: 50px;
    width: 140px;
    text-align: center;
    font-size: 19px;
    color: #373131;
    transition: color .3s;

    &:hover {
      color: #f37339;
    }
  }

  @media (max-width: 1200px) {
    width: 18%;
  }
`

const Depth2 = styled.ul`
  display: ${({ $open }) => $open ? 'block' : 'none'};
  position: absolute;
  z-index: 100;
  background: rgba(0,0,0,.6);
  width: 100%;
  top: 90px;
  border-top: 2px solid #f37339;
  box-sizing: border-box;
  padding: 10px 0;
  left: 0;

  li a {
    display: block;
    text-align: center;
    font-size: 16px;
    height: 40px;
    line-height: 40px;
    color: #fff;
    transition: color .3s;

    &:hover {
      color: #f37339;
    }
  }
`

const MenuBtn = styled.div`
  position: absolute;
  right: 0;
  top: 50%;
  margin-top: -25px;
  z-index: 100;
`

const AllMenuBtn = styled.button`
  display: block;
  width: 50px;
  height: 50px;
  line-height: 50px;
  text-indent: -9999px;
  background: url('/img/menu_ico.png') no-repeat center center;
  font-size: 0;
  color: #fff;
  text-align: center;
  border: none;
  cursor: pointer;

  &.on {
    background-image: url('/img/menu_close_ico.png');
  }
`

const AllGnb = styled.div`
  display: ${({ $open }) => $open ? 'block' : 'none'};
  position: absolute;
  top: 90px;
  left: 0;
  width: 100%;
  z-index: 101;
  background: rgba(0,0,0,.6);
`

const AllMenus = styled.div`
  float: right;
  display: flex;
`

const AllMenuGroup = styled.ul`
  width: 140px;
  padding: 10px 0;
  box-sizing: border-box;
  position: relative;
  float: left;

  &::before {
    position: absolute;
    top: 0;
    left: 50%;
    width: 0;
    height: 2px;
    background: #f37339;
    content: '';
    display: block;
    transition: all .3s;
  }

  &.on::before {
    left: 0;
    width: 100%;
  }

  li a {
    display: block;
    height: 40px;
    line-height: 40px;
    color: #fff;
    font-size: 16px;
    text-align: center;

    &:hover {
      color: #f37339;
    }
  }
`
