import React from "react";
import profile from "../assets/profile.png";

const Comment = (props) => {
  const styles = {
    wrapper: {
      margin: 8,
      padding: 8,
      display: "flex",
      flexDirection: "row",
      border: "1px solid grey",
      borderRadius: 16,
    },

    imageContainer: {},

    image: {
      width: 50,
      height: 50,
      borderRadius: 25,
    },

    contentContainer: {
      marginLeft: 8,
      display: "flex",
      flexDirection: "column",
      justifyContent: "center",
    },

    nameText: {
      color: "black",

      fontSize: 16,
      fontWeight: "bold",
    },

    commentText: {
      color: "black",
      fontSize: 16,
    },
  };

  return (
    <div style={styles.wrapper}>
      <div style={styles.imageContainer}>
        <img src={profile} alt="profile" style={styles.image} />
      </div>

      <div style={styles.contentContainer}>
        <span style={styles.nameText}>{props.name}</span>
        <span style={styles.commentText}>{props.text}</span>
      </div>
    </div>
  );
};

export default Comment;
