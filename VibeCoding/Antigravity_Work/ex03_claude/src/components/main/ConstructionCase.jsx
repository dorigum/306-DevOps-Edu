import styled from 'styled-components'

const caseItems = [
  { img: '/img/demo_01.jpg', category: '시트', title: '목질변멱재 우드' },
  { img: '/img/demo_01.jpg', category: '시트', title: '목질변멱재 우드' },
  { img: '/img/demo_01.jpg', category: '시트', title: '목질변멱재 우드' },
  { img: '/img/demo_01.jpg', category: '시트', title: '목질변멱재 우드' },
]

function ConstructionCase() {
  return (
    <Section>
      <div className="size">
        <SectionTitle>
          <h3>construction case</h3>
          <p>지속적인 변화와 혁신으로 더 나은 세상을 만들어가겠습니다</p>
        </SectionTitle>
        <Case>
          <CaseList>
            <ul className="clear">
              {caseItems.map((item, idx) => (
                <li key={idx}>
                  <a href="">
                    <ImgBox style={{ backgroundImage: `url('${item.img}')` }}>
                      <img src={item.img} alt="기본이미지" />
                    </ImgBox>
                    <TxtBox>
                      <p>{item.category}</p>
                      <Subject>
                        <b>{item.title}</b>
                        <span>+</span>
                      </Subject>
                    </TxtBox>
                  </a>
                </li>
              ))}
            </ul>
          </CaseList>
          <MoreBtn>
            <a href="#">더보기 <img src="/img/plus_w.png" alt="" /></a>
          </MoreBtn>
        </Case>
      </div>
    </Section>
  )
}

export default ConstructionCase

const Section = styled.section`
  padding: 60px 0;
  background: #f37339;
`

const SectionTitle = styled.div`
  text-align: center;

  h3 {
    font-family: 'Raleway', sans-serif;
    font-weight: 800;
    text-transform: uppercase;
    color: #fff;
    font-size: 45px;
    letter-spacing: -1px;
  }

  p {
    color: #fff;
    font-size: 18px;
    font-weight: 400;
    letter-spacing: -0.5px;
  }
`

const Case = styled.div`
  width: 100%;
  position: relative;
`

const CaseList = styled.div`
  width: 100%;
  position: relative;
  margin-top: 60px;

  ul {
    width: 102%;
    margin-left: -2%;
  }

  li {
    width: 23%;
    margin-left: 2%;
    float: left;

    a {
      display: block;
      position: relative;
    }
  }
`

const ImgBox = styled.p`
  background-repeat: no-repeat;
  background-size: cover;
  background-position: center center;

  img {
    width: 100%;
    height: auto;
    max-width: 100%;
    visibility: hidden;
    opacity: 0;
  }
`

const TxtBox = styled.div`
  padding: 15px 20px;
  box-sizing: border-box;
  background: #fff;

  p {
    color: #f37339;
    font-size: 16px;
    font-weight: 300;
  }
`

const Subject = styled.div`
  overflow: hidden;
  padding-right: 30px;
  box-sizing: border-box;
  position: relative;

  b {
    display: block;
    overflow: hidden;
    max-width: 100%;
    white-space: nowrap;
    text-overflow: ellipsis;
    color: #373131;
    font-size: 20px;
    font-weight: 600;
    letter-spacing: -0.5px;
  }

  span {
    display: block;
    position: absolute;
    right: 0;
    top: 50%;
    margin-top: -12.5px;
    width: 25px;
    height: 25px;
    border-radius: 25px;
    background: #f37339 url('/img/plus_w.png') no-repeat center center;
    text-indent: -9999px;
  }
`

const MoreBtn = styled.div`
  text-align: center;
  margin-top: 50px;

  a {
    display: inline-block;
    width: 200px;
    height: 50px;
    border: 2px solid #fff;
    box-sizing: border-box;
    text-align: center;
    border-radius: 50px;
    line-height: 46px;
    font-size: 16px;
    color: #fff;
    font-weight: 600;

    img {
      margin-top: -3px;
      margin-left: 10px;
    }
  }
`
