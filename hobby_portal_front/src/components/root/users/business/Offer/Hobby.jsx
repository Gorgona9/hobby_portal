import React from "react";
import Footer from "../../../fragments/footer/Footer";
import BackgroundHome from "../../../fragments/background/BackgroundHome";
import HobbyPages from "./HobbyPages";
import useMediaQuery from "beautiful-react-hooks/useMediaQuery";
import styles from "../../../../../css/Hobby.module.css";
import style from "../../../../../css/Footer.module.css";
import "react-confirm-alert/src/react-confirm-alert.css";

const Hobby = () => {
  const isColumnBasedSmall = useMediaQuery("(max-width: 900px)");
  console.log(isColumnBasedSmall);

  return (
    <>
      <main
        className={
          isColumnBasedSmall ? styles.hobby_main_small : styles.hobby_main
        }
      >
        <HobbyPages />
        <BackgroundHome />
      </main>

      <Footer class={style.footer_hobby_details} />
    </>
  );
};

export default Hobby;
