import styled from 'styled-components'

const notices = [
  { title: '성공건축자재 홈페이지를 오픈하였습니다.', date: '2018-08-01', isTop: true },
  { title: '성공건축자재 홈페이지를 오픈하였습니다.', date: '2018-08-01', isTop: false },
  { title: '성공건축자재 홈페이지를 오픈하였습니다.', date: '2018-08-01', isTop: false },
]

function BBSSection() {
  return (
    <Section>
      <div className="size">
        <BbsWrap>
          <div className="clear">
            <BbsBox className="not">
              <strong>공지사항</strong>
              <ul>
                {notices.map((notice, idx) => (
                  <li key={idx}>
                    <a href="" className="important">
                      <Subject>
                        {notice.isTop && <img src="/img/ico_top.png" alt="TOP공지" />}
                        {notice.title}
                      </Subject>
                      <DateSpan>{notice.date}</DateSpan>
                    </a>
                  </li>
                ))}
              </ul>
              <BtnMore>
                <a href="">더보기 <img src="/img/plus_r.png" alt="" /></a>
              </BtnMore>
            </BbsBox>

            <BbsBox className="cs">
              <strong>고객센터</strong>
              <p className="ico"><img src="/img/cs_ico.png" alt="" /></p>
              <a href="tel:02-1234-5678">02-1234-5678</a>
              <CsDesc>
                <p>FAX: 02-1234-5678</p>
                <p>E-MAIL: codro.ceo@codro.it</p>
              </CsDesc>
            </BbsBox>

            <BbsBox className="inq">
              <strong>견적문의</strong>
              <p className="ico"><img src="/img/inq_ico.png" alt="" /></p>
              <InqDesc>
                항상 최고의 품질을 제품들을 제공하기 위해 <br />
                성공건축자재는 항상 최선을 다하겠습니다.
              </InqDesc>
              <a href="#">견적문의하기 <img src="/img/plus_w.png" alt="" /></a>
            </BbsBox>
          </div>
        </BbsWrap>
      </div>
    </Section>
  )
}

export default BBSSection

const Section = styled.section`
  padding: 60px 0;
`

const BbsWrap = styled.div`
  width: 100%;
  position: relative;

  > .clear {
    width: 102%;
    margin-left: -2%;
  }
`

const BbsBox = styled.div`
  width: 31.3333%;
  margin-left: 2%;
  float: left;
  box-sizing: border-box;
  padding: 20px;
  border: 1px solid #dadada;
  min-height: 270px;

  > strong {
    display: block;
    text-align: center;
    color: #373131;
    font-size: 26px;
  }

  &.not ul {
    padding: 20px 0;
    min-height: 108px;

    li a {
      width: 100%;
      display: block;
      padding-right: 85px;
      box-sizing: border-box;
      border-bottom: 1px dotted #dadada;
      position: relative;
      line-height: 35px;
      transition: all .2s;

      &:hover {
        background: rgba(155,155,155,.1);
      }
    }
  }

  &.not .btn_more {
    text-align: center;

    a {
      display: inline-block;
      width: 130px;
      height: 35px;
      border-radius: 35px;
      border: 2px solid #f37339;
      color: #f37339;
      line-height: 31px;
      font-size: 16px;
      font-weight: 600;

      img {
        margin-top: -4px;
        margin-left: 4px;
      }
    }
  }

  .ico {
    padding: 20px 0;
    text-align: center;
  }

  &.cs {
    text-align: center;

    a {
      display: inline-block;
      text-align: center;
      color: #f37339;
      font-size: 32px;
      line-height: 1;
      font-weight: 900;
    }
  }

  &.inq {
    border: 0;
    background: #f3f3f3;
    text-align: center;

    > strong { color: #f37339; }

    > a {
      display: inline-block;
      margin: 20px auto 0;
      width: 160px;
      height: 35px;
      border-radius: 35px;
      line-height: 33px;
      background: #f37339;
      color: #fff;
      font-size: 16px;
      font-weight: 600;

      img {
        margin-top: -4px;
        margin-left: 4px;
      }
    }
  }
`

const Subject = styled.div`
  width: 100%;
  display: block;
  max-width: 100%;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  font-size: 16px;
  font-weight: 400;
  color: #333333;

  img {
    margin-right: 5px;
    vertical-align: middle;
  }
`

const DateSpan = styled.span`
  display: block;
  position: absolute;
  line-height: 35px;
  right: 0;
  top: 0;
  font-size: 15px;
  font-weight: 300;
  color: #828282;
`

const BtnMore = styled.div``

const CsDesc = styled.div`
  padding-top: 20px;
  color: #5c5b5e;
  font-size: 16px;
  font-weight: 300;
`

const InqDesc = styled.div`
  color: #5c5b5e;
  font-size: 16px;
  font-weight: 300;
`
